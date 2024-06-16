package com.Myblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commit {
    private  Integer id;
    //二选一，主评论才配有blog_id,子评论才配拥有father_id
    private Integer father_id;
    private Integer blog_id;
    //作者
    private Integer commiter_id;
    private String commit_text;
    private LocalDateTime create_time;
}
