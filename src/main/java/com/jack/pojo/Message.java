package com.jack.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private int id;
    private int msgCategory;
    private String msgContent;
    private Timestamp msgTime;
    private int userId;
    private int isRead;
}
