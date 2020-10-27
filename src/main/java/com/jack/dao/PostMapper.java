package com.jack.dao;

import com.jack.pojo.Post;

import java.util.List;
import java.util.Map;

public interface PostMapper {
    public Post queryPost(Map<String,Object> parameters);
    public Post queryPostAndUser(int postId);
    public List<Post> queryAllPost(Map<String,Object> parameters);
    public int updatePostById(int id);
    public int insertPost(Post post);
    public int getTotalCount(Map<String, Object> parameters);
    public int deletePost(int postId);
}
