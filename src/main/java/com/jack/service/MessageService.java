package com.jack.service;

import com.jack.pojo.Message;
import com.jack.pojo.PageBean;

public interface MessageService {
    public PageBean<Message> getAllMessageByPage(int currentPage, int pageSize);
    public boolean updateMessage(Message message);
    public boolean addMessage(Message message);
}
