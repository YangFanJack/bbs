package com.jack.dao;

import com.jack.pojo.Comment;

import java.util.List;
import java.util.Map;

public interface CommentMapper {
    public Comment queryComment(Map<String,Object> parameters);
    public List<Comment> queryAllComment(Map<String,Object> parameters);
    public int insertComment(Comment comment);
    public int getTotalCount(Map<String, Object> parameters);
}
