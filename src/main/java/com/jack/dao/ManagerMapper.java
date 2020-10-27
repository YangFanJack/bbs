package com.jack.dao;

import com.jack.pojo.Manager;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

public interface ManagerMapper {
    public Manager queryManager(Map<String,Object> parameters);
    public int getManagerIdByName(String username);
    public int updateManager(Manager manager);
    @Update("UPDATE manager SET noticeNum=noticeNum+1 where id=#{managerId}")
    public void addOneNoticeNum(int managerId);
}
