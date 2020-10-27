package com.jack.controller;

import com.jack.pojo.Manager;
import com.jack.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ManagerController {

    @Autowired
    @Qualifier("ManagerServiceImpl")
    private ManagerService managerService;

    @RequestMapping("managerLogin")
    public String login(Manager manager, String checkCode, Model model, HttpSession session){
        String msg;
        //验证码校验
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");

        //防止返回时验证码仍然有效
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkCode.equals("") || !checkCode.equalsIgnoreCase(checkcode_server)){
            msg="验证码错误，登录失败!";
            model.addAttribute("msg",msg);
            return "manager_login";
        }
        else {
            boolean isLogin = managerService.login(manager);
            if(isLogin){
                session.setAttribute("currentManagerId",managerService.getIdByName(manager.getUsername()));
                return "redirect:/certifyPost1";
            }
            else{
                msg = "用户名或者密码错误，请检查!";
                model.addAttribute("msg",msg);
                return "manager_login";
            }
        }
    }

    @RequestMapping("managerChangePwd")
    public String managerChangePwd(Manager manager, String checkCode, String password, String newPassword, String rePassword, Model model, HttpSession session){
        String msg;
        if(manager.getPassword()==null || newPassword==null || rePassword==null || password==null ||
                manager.getPassword().equals("") || newPassword.equals("") || rePassword.equals("") || password.equals("")){
            msg = "请填写完所有信息";
            model.addAttribute("msg",msg);
            return "manager_changePwd";
        }
//        从session获得managerId
        int managerId = 0;
        if(session.getAttribute("currentManagerId")!=null){
            managerId = (int) session.getAttribute("currentManagerId");
        }
        System.out.println(managerService.getManagerByIdPwd(managerId,password));
        //验证码校验
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        //防止返回时验证码仍然有效
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkCode.equals("") || !checkCode.equalsIgnoreCase(checkcode_server)){
            msg="验证码错误!";
            model.addAttribute("msg",msg);
            return "manager_changePwd";
        }
        else if(managerService.getManagerByIdPwd(managerId,password)==null){
            msg="初始密码错误，请检查!";
            model.addAttribute("msg",msg);
            return "user_changePwd";
        }
        else if(!newPassword.equals(rePassword)){
            msg="两次新密码不一致，请检查!";
            model.addAttribute("msg",msg);
            return "manager_changePwd";
        }
        else {
            manager.setId(managerId);
            manager.setPassword(newPassword);
            boolean isLogin = managerService.changePwd(manager);
            if(isLogin){
                msg="修改密码成功!";
                model.addAttribute("msg",msg);
                return "manager_changePwd";
            }
            else{
                msg = "系统出错，修改密码失败!";
                model.addAttribute("msg",msg);
                return "manager_changePwd";
            }
        }
    }
}
