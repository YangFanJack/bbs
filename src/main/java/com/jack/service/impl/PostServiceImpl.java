package com.jack.service.impl;

import com.jack.dao.PostMapper;
import com.jack.dao.UserMapper;
import com.jack.pojo.PageBean;
import com.jack.pojo.Post;
import com.jack.service.PostService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostServiceImpl implements PostService {
    private PostMapper postMapper;
    private UserMapper userMapper;

    public void setPostMapper(PostMapper postMapper) {
        this.postMapper = postMapper;
    }
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public PageBean<Post> getAllPostByPage(int currentPage, int pageSize, String searchString, int postCategory,int certifyState,int userId) {
        PageBean<Post> postPageBean = new PageBean<>();
        postPageBean.setCurrentPage(currentPage);
        postPageBean.setPageSize(pageSize);
        //调用dao查询总记录数
        Map<String,Object> map1 = new HashMap<>();
        if(searchString!=null){
            searchString = "%"+searchString+"%";
        }
        map1.put("postTitle",searchString);
        map1.put("postCategory",postCategory);
        map1.put("userId",userId);
        map1.put("certifyState",certifyState);
        int totalCount = postMapper.getTotalCount(map1);
        postPageBean.setTotalCount(totalCount);
        //调用dao查询list集合
        //计算开始的索引
        int start = (currentPage-1)*pageSize;
        Map<String,Object> map2 = new HashMap<>();
        map2.put("start",start);
        map2.put("pageSize",pageSize);
        map2.put("postTitle",searchString);
        map2.put("postCategory",postCategory);
        map2.put("userId",userId);
        map2.put("certifyState",certifyState);

        List<Post> posts = postMapper.queryAllPost(map2);
        postPageBean.setList(posts);

        //设置总页码
        int totalPage = (totalCount % pageSize ) == 0 ? totalCount/pageSize : (totalCount/pageSize)+1;
        postPageBean.setTotalPage(totalPage);
        return postPageBean;
    }

    @Override
    public Post getPostAndUser(int postId) {
        Post post = postMapper.queryPostAndUser(postId);
        return post;
    }

    @Override
    public int doPost(Post post) {
        return postMapper.insertPost(post);
    }

    @Override
    public int outPost(int postId) {
        return postMapper.deletePost(postId);
    }

    @Override
    public int passPost(Post post) {
        userMapper.addOnePostNum(post.getUserId().getId());
        return postMapper.updatePostById(post.getId());
    }

    @Override
    public boolean deletePost(Post post) {
        userMapper.addOneDelPostNum(post.getUserId().getId());
        int i = postMapper.deletePost(post.getId());
        if(i!=0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Post getPostById(int id) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        Post post = postMapper.queryPost(map);
        return post;
    }
}
