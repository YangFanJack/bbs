package com.jack.service;

import com.jack.pojo.PageBean;
import com.jack.pojo.Post;

public interface PostService {
    public PageBean<Post> getAllPostByPage(int currentPage,int pageSize,String searchString,int postCategory,int certifyState,int userId);
    public Post getPostAndUser(int postId);
    public int doPost(Post post);
    public int outPost(int postId);
    public int passPost(Post post);
    public boolean deletePost(Post post);
    public Post getPostById(int id);
}
