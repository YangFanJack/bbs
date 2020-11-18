package com.jack.controller;

import com.jack.pojo.Message;
import com.jack.pojo.PageBean;
import com.jack.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MessageController {
    @Autowired
    @Qualifier("MessageServiceImpl")
    private MessageService messageService;

    @RequestMapping("showMessage")
    public String showMessage(String totalPage, String currentPage, Model model, HttpSession session){
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
        PageBean<Message> messagePageBean = messageService.getAllMessageByPage(currentPage0, pageSize);
        model.addAttribute("messagePageBean",messagePageBean);
        return "user_showMessage";
    }

    @RequestMapping("readMessage")
    public String readMessage(String messageId, Model model, HttpSession session){
        int messageId0 = Integer.parseInt(messageId);
        Message message = new Message();
        message.setId(messageId0);
        boolean b = messageService.updateMessage(message);
        return "redirect:/showMessage";
    }
}
