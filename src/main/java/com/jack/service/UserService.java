package com.jack.service;

import com.jack.pojo.PageBean;
import com.jack.pojo.User;

public interface UserService {
    public boolean login(User user);
    public boolean register(User user);
    public User getUserByName(String username);
    public User getUserById(int id);
    public int getIdByName(String username);
    public boolean changePwd(User user);
    public User getUserByIdPwd(int userId,String password);
    public PageBean<User> getAllUserByPage(int currentPage, int pageSize);
    public boolean deleteUser(int userId);
}
