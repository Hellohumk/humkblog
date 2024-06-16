package com.Myblog.mapper;

import com.Myblog.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE user_name = #{user_name} and password = #{password}")
    User getByusernameandpassword(User user);

    @Update("update user set user_commit_sum = user_commit_sum + 1 where id = #{id}")
    void UserCommitSumPlusOne(Integer id);

    @Update("update user set user_commit_sum = user_commit_sum - 1 where id = #{id}")
    void UserCommitSumMinusOne(Integer id);

    @Update("update user set user_blog_sum = user_blog_sum - 1 where id = #{id} ")
    void UserBlogSumMinusOne(Integer uid);

    @Update("update user set user_blog_sum = user_blog_sum + 1 where id = #{id} ")
    void UserBlogSumPlusOne(Integer id);

    @Update("update user set exp = exp + 100 where id = #{id}")
    void UserExpUp(Integer id);

    @Select("select * from user where id = #{id}")
    User getByid(Integer id);


    int UpdateUser(User user);
}
