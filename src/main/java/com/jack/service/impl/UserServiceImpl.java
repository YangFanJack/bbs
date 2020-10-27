package com.jack.service.impl;

import com.jack.dao.UserMapper;
import com.jack.pojo.PageBean;
import com.jack.pojo.User;
import com.jack.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean login(User user) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("username",user.getUsername());
        parameters.put("password",user.getPassword());
        User result = userMapper.queryUser(parameters);
        if(result!=null){
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public boolean register(User user) {
        try {
            int result = userMapper.insertUser(user);
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
    public User getUserByName(String username) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("username",username);
        User user = userMapper.queryUser(parameters);
        return user;
    }

    @Override
    public User getUserById(int id) {
        Map<String,Object> parameters = new HashMap<>();
        parameters.put("id",id);
        User user = userMapper.queryUser(parameters);
        return user;
    }

    @Override
    public int getIdByName(String username) {
        return userMapper.getUserIdByName(username);
    }

    @Override
    public boolean changePwd(User user) {
        try {
            int result = userMapper.updateUser(user);
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
    public User getUserByIdPwd(int userId,String password) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",userId);
        map.put("password",password);
        return userMapper.queryUser(map);
    }

    @Override
    public PageBean<User> getAllUserByPage(int currentPage, int pageSize) {
        PageBean<User> userPageBean = new PageBean<>();
        userPageBean.setCurrentPage(currentPage);
        userPageBean.setPageSize(pageSize);
        //调用dao查询总记录数
        Map<String,Object> map1 = new HashMap<>();
        int totalCount = userMapper.getTotalCount(map1);
        userPageBean.setTotalCount(totalCount);
        //调用dao查询list集合
        //计算开始的索引
        int start = (currentPage-1)*pageSize;
        Map<String,Object> map2 = new HashMap<>();
        map2.put("start",start);
        map2.put("pageSize",pageSize);

        List<User> posts = userMapper.queryAllUser(map2);
        userPageBean.setList(posts);

        //设置总页码
        int totalPage = (totalCount % pageSize ) == 0 ? totalCount/pageSize : (totalCount/pageSize)+1;
        userPageBean.setTotalPage(totalPage);
        return userPageBean;
    }

    @Override
    public boolean deleteUser(int userId) {
        int i = userMapper.deleteUser(userId);
        if(i!=0){
            return true;
        }
        else{
            return false;
        }
    }
}
