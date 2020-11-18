package com.jack.service.impl;

import com.jack.dao.ManagerMapper;
import com.jack.dao.NoticeMapper;
import com.jack.pojo.Notice;
import com.jack.pojo.PageBean;
import com.jack.service.NoticeService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoticeServiceImpl implements NoticeService {
    private NoticeMapper noticeMapper;
    private ManagerMapper managerMapper;

    public void setNoticeMapper(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }
    public void setManagerMapper(ManagerMapper managerMapper) {
        this.managerMapper = managerMapper;
    }

    @Override
    public PageBean<Notice> getAllNoticeByPage(int currentPage, int pageSize) {
        PageBean<Notice> noticePageBean = new PageBean<>();
        noticePageBean.setCurrentPage(currentPage);
        noticePageBean.setPageSize(pageSize);
        //调用dao查询总记录数
        Map<String,Object> map1 = new HashMap<>();
        int totalCount = noticeMapper.getTotalCount();
        noticePageBean.setTotalCount(totalCount);
        //调用dao查询list集合
        //计算开始的索引
        int start = (currentPage-1)*pageSize;
        Map<String,Object> map2 = new HashMap<>();
        map2.put("start",start);
        map2.put("pageSize",pageSize);
        List<Notice> notices = noticeMapper.queryAllNotice(map2);
        noticePageBean.setList(notices);
        //设置总页码
        int totalPage = (totalCount % pageSize ) == 0 ? totalCount/pageSize : (totalCount/pageSize)+1;
        noticePageBean.setTotalPage(totalPage);

        return noticePageBean;
    }

    @Override
    public List<Notice> getSomeNotice(int counts) {
        Map<String, Object> map = new HashMap<>();
        map.put("counts",counts);
        List<Notice> notices = noticeMapper.queryAllNotice(map);
        return notices;
    }

    @Override
    public int doNotice(Notice notice) {
        managerMapper.addOneNoticeNum(notice.getManagerId().getId());
        return noticeMapper.insertNotice(notice);
    }

    @Override
    public Notice getNotice(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id",id);
        Notice notice = noticeMapper.queryNotice(map);
        return notice;
    }

    @Override
    public boolean deleteNotice(int id) {
        int i = noticeMapper.deleteNotice(id);
        if(i!=0){
            return true;
        }
        else{
            return false;
        }
    }
}
