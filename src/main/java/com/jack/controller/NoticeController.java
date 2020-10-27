package com.jack.controller;

import com.jack.pojo.Manager;
import com.jack.pojo.Notice;
import com.jack.pojo.PageBean;
import com.jack.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Controller
public class NoticeController {
    @Autowired
    @Qualifier("NoticeServiceImpl")
    private NoticeService noticeService;

    @RequestMapping("showNotice")
    public String showNotice(String totalPage, String currentPage, Model model, HttpSession session){
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
        PageBean<Notice> noticePageBean = noticeService.getAllNoticeByPage(currentPage0, pageSize);
        model.addAttribute("noticePageBean",noticePageBean);
        return "notice";
    }

    @RequestMapping("doNotice")
    public String doNotice(String noticeTitle, String noticeContent, Model model, HttpSession session){
        int managerId = 0;
        if(session.getAttribute("currentManagerId")!=null){
            managerId = (int) session.getAttribute("currentManagerId");
        }
        Notice notice = new Notice();
        notice.setNoticeTitle(noticeTitle);
        notice.setNoticeContent(noticeContent);
        Manager manager = new Manager();
        manager.setId(managerId);
        notice.setManagerId(manager);
        Timestamp t = new Timestamp(System.currentTimeMillis());
        notice.setNoticeTime(t);

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(notice);
        //处理Notice
        if(noticeContent!=null && !noticeContent.equals("") && noticeTitle!=null && !noticeTitle.equals("")){
            int i = noticeService.doNotice(notice);
            if(i != 0){
                model.addAttribute("msg","发布通知成功!");
            }
            if(i == 0){
                model.addAttribute("msg","系统错误，发布失败!");
            }
        }
        else {
            model.addAttribute("msg","内容不能为空!");
        }
        return "manager_doNotice";
    }
}
