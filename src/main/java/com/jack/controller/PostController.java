package com.jack.controller;

import com.jack.pojo.Comment;
import com.jack.pojo.PageBean;
import com.jack.pojo.Post;
import com.jack.pojo.User;
import com.jack.service.CommentService;
import com.jack.service.MailSenderSrvService;
import com.jack.service.PostService;
import com.jack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    @Qualifier("PostServiceImpl")
    private PostService postService;
    @Autowired
    @Qualifier("CommentServiceImpl")
    private CommentService commentService;
    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;
    @Autowired
    @Qualifier("MailSenderSrvServiceImpl")
    private MailSenderSrvService mailSenderSrvService;

    @RequestMapping("detailPost")
    public String detailPost(@ModelAttribute("msg") String msg, String postId, Model model, HttpSession session){
        System.out.println("拿到重定向得到的参数msg:" + msg);
        model.addAttribute("msg", msg);
        //处理Post
        int postId0 = Integer.parseInt(postId);
        Post postAndUser = postService.getPostAndUser(postId0);
        model.addAttribute("postAndUser",postAndUser);
        List<Comment> comments = commentService.getCommentsByPostId(postId0);
        System.out.println("++++++++++++++++++++");
        System.out.println(comments);
        model.addAttribute("comments",comments);
        return "detailPost";
    }
    @RequestMapping("showPost1")
    public String showPost1(String totalPage, String currentPage, Model model, HttpSession session){
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
        //处理Post
        int userId = 0;
        if(session.getAttribute("currentId")!=null){
            userId = (int) session.getAttribute("currentId");
        }

        int pageSize = 5;
        PageBean<Post> postPageBean = postService.getAllPostByPage(currentPage0, pageSize,null,0,0, userId);
        model.addAttribute("postPageBean",postPageBean);
        return "user_showPost1";
    }
    @RequestMapping("showPost2")
    public String showPost2(String postId, Model model, HttpSession session){
        //处理Post
        int postId0 = Integer.parseInt(postId);
        Post postAndUser = postService.getPostAndUser(postId0);
        model.addAttribute("postAndUser",postAndUser);
        List<Comment> comments = commentService.getCommentsByPostId(postId0);
        model.addAttribute("comments",comments);
        return "user_showPost2";
    }

    @RequestMapping("doPost")
    public String doPost(String postTitle, int postCategory, String postContent,Model model, HttpSession session){
        int userId = 0;
        if(session.getAttribute("currentId")!=null){
            userId = (int) session.getAttribute("currentId");
        }
        Post post = new Post();
        post.setPostTitle(postTitle);
        post.setPostContent(postContent);
        post.setCertifyState(1);
        User user = new User();
        user.setId(userId);
        post.setUserId(user);
        post.setPostCategory(postCategory);
        Timestamp t = new Timestamp(System.currentTimeMillis());
        post.setPostTime(t);

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(post);
        //处理Post
        if(postContent!=null && !postContent.equals("") && postTitle!=null && !postTitle.equals("")){
            int i = postService.doPost(post);
            if(i != 0){
                model.addAttribute("msg","发帖成功!");
            }
            if(i == 0){
                model.addAttribute("msg","系统错误，发帖失败!");
            }
        }
        else {
            model.addAttribute("msg","内容不能为空!");
        }
        return "user_doPost";
    }


    @RequestMapping("certifyPost1")
    public String certifyPost1(String totalPage, String currentPage, Model model, HttpSession session){
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
        //处理Post

        int pageSize = 5;
        PageBean<Post> postPageBean = postService.getAllPostByPage(currentPage0, pageSize,null,0,1, 0);
        model.addAttribute("postPageBean",postPageBean);
        return "manager_certifyPost1";
    }

    @RequestMapping("certifyPost2")
    public String certifyPost2(String postId, Model model, HttpSession session){
        //处理Post
        int postId0 = Integer.parseInt(postId);
        Post postAndUser = postService.getPostAndUser(postId0);
        model.addAttribute("postAndUser",postAndUser);
        return "manager_certifyPost2";
    }

    @RequestMapping("outPost")
    public String outPost(String postId, Model model, HttpSession session){
        int postId0 = Integer.parseInt(postId);
        postService.outPost(postId0);
        return "redirect:/certifyPost1";
    }

    @RequestMapping("passPost")
    public String passPost(String postId, Model model, HttpSession session){
        int postId0 = Integer.parseInt(postId);
        postService.passPost(postId0);
        return "redirect:/certifyPost1";
    }

    @RequestMapping("showAllPost")
    public String showAllPost(String totalPage, String currentPage, Model model, HttpSession session){
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

        int pageSize = 5;
        PageBean<Post> postPageBean = postService.getAllPostByPage(currentPage0, pageSize,null, 0, 0, 0);
        model.addAttribute("postPageBean",postPageBean);
        return "manager_managePost";
    }

    @RequestMapping("deletePost")
    public String deletePost(String postId, Model model, HttpSession session){
        int postId0 = 0;
        if(postId!=null&&!postId.equals("")){
            postId0 = Integer.parseInt(postId);
        }
//        发提示邮件
        Post post = postService.getPostById(postId0);
        Post postAndUser = postService.getPostAndUser(postId0);
        User user = userService.getUserById(postAndUser.getUserId().getId());
        String mail = user.getEmail();
        String subject = "一人论坛删除贴子通知";
        String text = "由于您的标题为'"+post.getPostTitle()+"'的贴子内容违反网站规定，现已删除您的这篇贴子，与这篇贴子有关的所有内容已被删除，望周知。";
        mailSenderSrvService.sendEmail(mail,subject,text);
//        删帖
        boolean result = postService.deletePost(postId0);
        String msg;
        if(result){
            msg="删除贴子成功!";
        }
        else {
            msg="删除贴子失败!";
        }
        model.addAttribute("msg",msg);
        return "redirect:/showAllPost";
    }
}
