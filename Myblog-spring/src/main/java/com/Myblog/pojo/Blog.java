package com.Myblog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    private Integer id;
    private Integer writer_id;
    private Integer likes_sum;
    private Integer commit_sum;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
    private String blog_text;
    private String blog_topic;
}
