package com.Myblog.service;

import com.Myblog.pojo.Blog;
import com.Myblog.pojo.Commit;
import com.Myblog.pojo.User;

import java.util.List;

public interface PersonService {
    User login(User user);

    void DeleteLike(Integer blog_id);

    void SaveLike(Integer blogId);

    List<Blog> PersonalBlogsList();
    Integer DeleteBlog(Integer id);

    void SaveBlog(Blog blog);

    Integer UpdateBlog(Blog blog);

    User Userlist();

    int UpdateUser(User user);

    User GetUserByid(Integer id);

    List<Integer> GetPersonalCommit();
}
