package com.jack.controller;

import com.jack.pojo.*;
import com.jack.service.CommentService;
import com.jack.service.MessageService;
import com.jack.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Controller
public class CommentController {
    @Autowired
    @Qualifier("CommentServiceImpl")
    private CommentService commentService;
    @Autowired
    @Qualifier("PostServiceImpl")
    private PostService postService;
    @Autowired
    @Qualifier("MessageServiceImpl")
    private MessageService messageService;

    @RequestMapping("doComment")
    public String doComment(String commentContent,String postId, Model model, HttpSession session){
        int postId0 = 0;
        if(postId!=null&&!postId.equals("")){
            postId0 = Integer.parseInt(postId);
        }
        int userId = 0;
        if(session.getAttribute("currentId")!=null){
            userId = (int) session.getAttribute("currentId");
        }
        Comment comment = new Comment();
        comment.setCommentContent(commentContent);

        Post post = new Post();
        post.setId(postId0);
        comment.setPostId(post);

        User user = new User();
        user.setId(userId);
        comment.setUserId(user);

        Timestamp t = new Timestamp(System.currentTimeMillis());
        comment.setCommentTime(t);

        //处理Comment
        if(commentContent!=null && !commentContent.equals("")){
            //发评论消息
            Post postAndUser = postService.getPostAndUser(postId0);
            Message message = new Message();
            message.setMsgCategory(1);
            message.setMsgContent("用户"+postAndUser.getUserId().getUsername()+"在你的标题为 <span style=\"color:orange\">\""+postAndUser.getPostTitle()+"\"</span> 的贴子下评论了，<a href='detailPost?postId="+postAndUser.getId()+"'>快去看看吧！</a>");
            message.setMsgTime(t);
            message.setUserId(postAndUser.getUserId().getId());
            message.setIsRead(1);
            boolean b = messageService.addMessage(message);
            int i = commentService.doComment(comment);
            if(i != 0){
                model.addAttribute("msg","提交成功!");
            }
            if(i == 0){
                model.addAttribute("msg","系统错误，提交失败!");
            }
        }
        else {
            model.addAttribute("msg","评论内容不能为空!");
        }
        return "redirect:/detailPost?postId="+postId0;
    }

    @RequestMapping("showAllCommentByUser")
    public String showAllCommentByUser(String totalPage, String currentPage, Model model, HttpSession session){
        //处理Comment
        int currentPage0 = 0;
        if(currentPage!=null&&!currentPage.equals("")){
            currentPage0 = Integer.parseInt(currentPage);
            if(currentPage0 <= 1){
                currentPage0 = 1;
            }
        }
        if(totalPage!=null&&!totalPage.equals("")){
            if(Integer.parseInt(currentPage) >= Integer.parseInt(totalPage)){
                currentPage0 = Integer.parseInt(totalPage);
            }
        }
        if (currentPage0 == 0){
            currentPage0=1;
        }
        //处理Notice
        int pageSize = 5;
        int userId = 0;
        if(session.getAttribute("currentId")!=null){
            userId = (int) session.getAttribute("currentId");
        }
        PageBean<Comment> commentPageBean = commentService.getAllCommentByPage(currentPage0, pageSize, userId);
        model.addAttribute("commentPageBean",commentPageBean);
        return "user_comment";
    }
}
