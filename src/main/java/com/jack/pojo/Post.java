package com.jack.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private int id;
    private String postTitle;
    private String postContent;
//    private int userId;
    private User userId;
    private Timestamp postTime;
    private int postCategory;
    private int certifyState;
    private List<Comment> commentList;
}
