package com.jack.dao;

import com.jack.pojo.Notice;

import java.util.List;
import java.util.Map;

public interface NoticeMapper {
    public Notice queryNotice(Map<String,Object> parameters);
    public List<Notice> queryAllNotice(Map<String,Object> parameters);
    public int insertNotice(Notice notice);
    public int getTotalCount();
}
