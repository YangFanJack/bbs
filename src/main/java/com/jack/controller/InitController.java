package com.jack.controller;

import com.jack.pojo.Notice;
import com.jack.pojo.PageBean;
import com.jack.pojo.Post;
import com.jack.pojo.User;
import com.jack.service.NoticeService;
import com.jack.service.PostService;
import com.jack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class InitController {
    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;
    @Autowired
    @Qualifier("PostServiceImpl")
    private PostService postService;
    @Autowired
    @Qualifier("NoticeServiceImpl")
    private NoticeService noticeService;

    @RequestMapping("initIndex")
    public String initIndex(String totalPage, String currentPage, String searchString, String postCategory, String certifyState, Model model, HttpSession session){

        int currentPage0 = 0;
        int postCategory0 = 1;
        session.setAttribute("currentCategory",1);
        int certifyState0 = 0;
        if(currentPage!=null&&!currentPage.equals("")){
            currentPage0 = Integer.parseInt(currentPage);
            if(currentPage0 <= 1){
                currentPage0 = 1;
            }
        }
        if(postCategory!=null&&!postCategory.equals("")){
            postCategory0 = Integer.parseInt(postCategory);
            switch (postCategory0){
                case 1:
                    session.setAttribute("currentCategory",1);
                    break;
                case 2:
                    session.setAttribute("currentCategory",2);
                    break;
                case 3:
                    session.setAttribute("currentCategory",3);
                    break;
                case 4:
                    session.setAttribute("currentCategory",4);
                    break;
            }
        }
        if(certifyState!=null&&!certifyState.equals("")){
            certifyState0 = Integer.parseInt(certifyState);
        }
        if(totalPage!=null&&!totalPage.equals("")){
            if(Integer.parseInt(currentPage) >= Integer.parseInt(totalPage)){
                currentPage0 = Integer.parseInt(totalPage);
            }
        }

        int userId = 0;
//        if(session.getAttribute("currentId")!=null){
//            userId = (int) session.getAttribute("currentId");
//        }


        //处理User
        if(session.getAttribute("currentUser")!=null){
            String username = (String)session.getAttribute("currentUser");
            User userInfo = userService.getUserByName(username);
            model.addAttribute("userInfo",userInfo);
        }

        //处理Post
        if (certifyState0 == 0){
            certifyState0=2;
        }
        if (currentPage0 == 0){
            currentPage0=1;
        }
        int pageSize = 6;
        PageBean<Post> postPageBean = postService.getAllPostByPage(currentPage0, pageSize, searchString, postCategory0, certifyState0, userId);
        model.addAttribute("postPageBean",postPageBean);
        //处理Notice
        int counts = 4;
        List<Notice> someNotice = noticeService.getSomeNotice(counts);
        model.addAttribute("someNotice",someNotice);

        return "index";
    }

}
