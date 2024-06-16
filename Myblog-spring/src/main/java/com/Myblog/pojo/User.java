package com.Myblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String user_name;
    private String password;
    private Integer gender;
    private Integer exp;
    private String image;//头像的url
    private LocalDateTime create_time;
    private LocalDateTime last_login_time;
    private boolean if_ban;
    private Integer user_blog_sum;
    private Integer user_commit_sum;


}
