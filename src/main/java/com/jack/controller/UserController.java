package com.jack.controller;

import com.jack.pojo.PageBean;
import com.jack.pojo.User;
import com.jack.service.MailSenderSrvService;
import com.jack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;
    @Autowired
    @Qualifier("MailSenderSrvServiceImpl")
    private MailSenderSrvService mailSenderSrvService;

    @RequestMapping("userLogin")
    public String login(User user, String checkCode, Model model, HttpSession session){
        String msg;
        //验证码校验
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //防止返回时验证码仍然有效
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkCode.equals("") || !checkCode.equalsIgnoreCase(checkcode_server)){
            msg="验证码错误，登录失败!";
            model.addAttribute("msg",msg);
            return "user_login";
        }
        else {
            boolean isLogin = userService.login(user);
            if(isLogin){
                //将用户名存进session中
                session.setAttribute("currentUser",user.getUsername());
                session.setAttribute("currentId",userService.getIdByName(user.getUsername()));
                return "redirect:showPost1";
            }
            else{
                msg = "用户名或者密码错误，请检查!";
                model.addAttribute("msg",msg);
                return "user_login";
            }
        }

    }
    @RequestMapping("userRegister")
    public String register(User user, String checkCode, String newPassword, Model model, HttpSession session){
        String msg;
        if(user.getUsername()==null || user.getEmail()==null || user.getPassword()==null ||
        user.getUsername().equals("") || user.getEmail().equals("") || user.getPassword().equals("")){
            msg = "请填写完所有信息";
            model.addAttribute("msg",msg);
            return "user_reg";
        }
        //验证码校验
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //防止返回时验证码仍然有效
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkCode.equals("") || !checkCode.equalsIgnoreCase(checkcode_server)){
            msg="验证码错误，注册失败!";
            model.addAttribute("msg",msg);
            return "user_reg";
        }
        if(!user.getPassword().equals(newPassword)){
            msg="两次密码不一致，请检查!";
            model.addAttribute("msg",msg);
            return "user_reg";
        }
        else {
            boolean isLogin = userService.register(user);
            if(isLogin){
                msg="注册成功，请登录!";
                model.addAttribute("msg",msg);
                return "user_login";
            }
            else{
                msg = "用户名或者邮箱已被注册!";
                model.addAttribute("msg",msg);
                return "user_reg";
            }
        }
    }

    @RequestMapping("userExit")
    public String exit(Model model, HttpSession session){
        session.removeAttribute("currentUser");
        session.removeAttribute("currentId");
        return "redirect:/initIndex";
    }

    @RequestMapping("userChangePwd")
    public String userChangePwd(User user, String checkCode, String password, String newPassword, String rePassword, Model model, HttpSession session){
        String msg;
        if(user.getPassword()==null || newPassword==null || rePassword==null || password==null ||
        user.getPassword().equals("") || newPassword.equals("") || rePassword.equals("") || password.equals("")){
            msg = "请填写完所有信息";
            model.addAttribute("msg",msg);
            return "user_changePwd";
        }
//        从session获得userId
        int userId = 0;
        if(session.getAttribute("currentId")!=null){
            userId = (int) session.getAttribute("currentId");
        }
        //验证码校验
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //防止返回时验证码仍然有效
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkCode.equals("") || !checkCode.equalsIgnoreCase(checkcode_server)){
            msg="验证码错误!";
            model.addAttribute("msg",msg);
            return "user_changePwd";
        }
        else if(userService.getUserByIdPwd(userId,password)==null){
            msg="初始密码错误，请检查!";
            model.addAttribute("msg",msg);
            return "user_changePwd";
        }
        else if(!newPassword.equals(rePassword)){
            msg="两次新密码不一致，请检查!";
            model.addAttribute("msg",msg);
            return "user_changePwd";
        }
        else {
            user.setId(userId);
            user.setPassword(newPassword);
            boolean isLogin = userService.changePwd(user);
            if(isLogin){
                msg="修改密码成功!";
                model.addAttribute("msg",msg);
                return "user_changePwd";
            }
            else{
                msg = "系统出错，修改密码失败!";
                model.addAttribute("msg",msg);
                return "user_changePwd";
            }
        }
    }

    @RequestMapping("showAllUser")
    public String showAllUser(String totalPage, String currentPage, Model model, HttpSession session){
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
        PageBean<User> userPageBean = userService.getAllUserByPage(currentPage0, pageSize);
        model.addAttribute("userPageBean",userPageBean);
        return "manager_manageUser";
    }

    @RequestMapping("deleteUser")
    public String deleteUser(String userId, Model model, HttpSession session){
        int userId0 = 0;
        if(userId!=null&&!userId.equals("")){
            userId0 = Integer.parseInt(userId);
        }
//        发提示邮件
        User user = userService.getUserById(userId0);
        String mail = user.getEmail();
        String subject = "一人论坛注销帐号通知";
        String text = "由于您严重违反网站规则，现已注销您的帐号，与您帐号有关的所有内容已被删除，请重新申请帐号，望周知。";
        mailSenderSrvService.sendEmail(mail,subject,text);
//        删除
        boolean result = userService.deleteUser(userId0);
        String msg;
        if(result){
            msg="删除用户成功!";
        }
        else {
            msg="删除用户失败!";
        }
        model.addAttribute("msg",msg);
        return "redirect:/showAllUser";
    }
}
