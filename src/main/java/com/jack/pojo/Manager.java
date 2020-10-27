package com.jack.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {
    private int id;
    private String username;
    private String password;
    private int noticeNum;
    private List<Notice> noticeList;
}
