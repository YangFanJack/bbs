package com.jack.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private int postNum;
    private int commentNum;
    private int delPostNum;
    private int status;
    private String activeCode;
    private List<Comment> commentList;
    private List<Post> postList;
}
