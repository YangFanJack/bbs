package com.jack.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    private int id;
    private String noticeTitle;
    private String noticeContent;
    private Timestamp noticeTime;
    private Manager managerId;
}