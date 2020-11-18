package com.jack.dao;

import com.jack.pojo.Message;

import java.util.List;
import java.util.Map;

public interface MessageMapper {
    public int insertMessage(Message message);
    public int updateMessage(int id);
    public List<Message> queryAllMessage(Map<String,Object> parameters);
    public int getTotalCount(Map<String, Object> parameters);
}
