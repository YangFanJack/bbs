package com.jack.service;

import com.jack.pojo.Comment;
import com.jack.pojo.PageBean;

import java.util.List;

public interface CommentService {
    public PageBean<Comment> getAllCommentByPage(int currentPage, int pageSize, int userId);
    public int doComment(Comment comment);
    public List<Comment> getCommentsByPostId(int postId);
}
