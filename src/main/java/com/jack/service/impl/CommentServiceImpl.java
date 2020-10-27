package com.jack.service.impl;

import com.jack.dao.CommentMapper;
import com.jack.dao.UserMapper;
import com.jack.pojo.Comment;
import com.jack.pojo.PageBean;
import com.jack.service.CommentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentServiceImpl implements CommentService {
    private CommentMapper commentMapper;
    private UserMapper userMapper;

    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public PageBean<Comment> getAllCommentByPage(int currentPage, int pageSize, int userId) {
        PageBean<Comment> commentPageBean = new PageBean<>();
        commentPageBean.setCurrentPage(currentPage);
        commentPageBean.setPageSize(pageSize);
        //调用dao查询总记录数
        Map<String,Object> map1 = new HashMap<>();
        map1.put("userId",userId);
        int totalCount = commentMapper.getTotalCount(map1);
        commentPageBean.setTotalCount(totalCount);
        //调用dao查询list集合
        //计算开始的索引
        int start = (currentPage-1)*pageSize;
        Map<String,Object> map2 = new HashMap<>();
        map2.put("start",start);
        map2.put("pageSize",pageSize);
        map2.put("userId",userId);

        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println(map2);

        List<Comment> comments = commentMapper.queryAllComment(map2);
        commentPageBean.setList(comments);
        //设置总页码
        int totalPage = (totalCount % pageSize ) == 0 ? totalCount/pageSize : (totalCount/pageSize)+1;
        commentPageBean.setTotalPage(totalPage);
        return commentPageBean;
    }

    @Override
    public int doComment(Comment comment) {
        userMapper.addOneCommentNum(comment.getUserId().getId());
        return commentMapper.insertComment(comment);
    }

    @Override
    public List<Comment> getCommentsByPostId(int postId) {
        Map<String,Object> map = new HashMap<>();
        map.put("postId",postId);
        List<Comment> comments = commentMapper.queryAllComment(map);
        return comments;
    }
}
