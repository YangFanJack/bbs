package com.jack.service.impl;

import com.jack.dao.MessageMapper;
import com.jack.pojo.Message;
import com.jack.pojo.PageBean;
import com.jack.service.MessageService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageServiceImpl implements MessageService {
    private MessageMapper messageMapper;

    public void setMessageMapper(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    @Override
    public PageBean<Message> getAllMessageByPage(int currentPage, int pageSize) {
        PageBean<Message> messagePageBean = new PageBean<>();
        messagePageBean.setCurrentPage(currentPage);
        messagePageBean.setPageSize(pageSize);
        //调用dao查询总记录数
        Map<String,Object> map1 = new HashMap<>();
        int totalCount = messageMapper.getTotalCount(map1);
        messagePageBean.setTotalCount(totalCount);
        //调用dao查询list集合
        //计算开始的索引
        int start = (currentPage-1)*pageSize;
        Map<String,Object> map2 = new HashMap<>();
        map2.put("start",start);
        map2.put("pageSize",pageSize);

        List<Message> posts = messageMapper.queryAllMessage(map2);
        messagePageBean.setList(posts);

        //设置总页码
        int totalPage = (totalCount % pageSize ) == 0 ? totalCount/pageSize : (totalCount/pageSize)+1;
        messagePageBean.setTotalPage(totalPage);
        return messagePageBean;
    }

    @Override
    public boolean updateMessage(Message message) {
        try {
            int result = messageMapper.updateMessage(message.getId());
            if(result!=0){
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean addMessage(Message message) {
        try {
            int result = messageMapper.insertMessage(message);
            if(result!=0){
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception e){
            return false;
        }
    }
}
