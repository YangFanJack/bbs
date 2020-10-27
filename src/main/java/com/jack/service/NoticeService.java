package com.jack.service;

import com.jack.pojo.Notice;
import com.jack.pojo.PageBean;

import java.util.List;

public interface NoticeService{
    public PageBean<Notice> getAllNoticeByPage(int currentPage, int pageSize);
    public List<Notice> getSomeNotice(int counts);
    public int doNotice(Notice notice);
}
