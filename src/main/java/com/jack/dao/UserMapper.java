package com.jack.dao;

import com.jack.pojo.User;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    public User queryUser(Map<String,Object> parameters);
    public List<User> queryAllUser(Map<String,Object> parameters);
    public int updateUser(User user);
    public int insertUser(User user);
    public int getUserIdByName(String username);
    public int getTotalCount(Map<String, Object> parameters);
    @Update("UPDATE `user` SET commentNum=commentNum+1 where id=#{userId}")
    public void addOneCommentNum(int userId);
    @Update("UPDATE `user` SET postNum=postNum+1 where id=#{userId}")
    public void addOnePostNum(int userId);
    public int deleteUser(int userId);
}
