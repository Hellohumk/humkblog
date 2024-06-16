package com.Myblog.mapper;

import com.Myblog.pojo.Blog;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface BlogMapper {
    @Select("Select * from blog")
    List<Blog> list();

    @Select("SELECT * from blog where id = #{id}")
    Blog getBlogByid(Integer id);


    @Update("UPDATE blog set commit_sum = commit_sum + 1 where id = #{blogId}")
    void BlogCommitSumPlusOne(Integer blogId);

//  @Select("select * from blog where id in (1,2)")
    List<Blog> GetBlogsByids(List<Integer> blogids);

    @Update("update blog set likes_num = likes_num - 1 where id = #{blogId}")
    void LikeSumMinusOneByBlogid(Integer blogId);

    @Update("update blog set likes_num = likes_num + 1 where id = #{blogId}")
    void LikeSumPlusOneByBlogid(Integer blogId);

    @Select("SELECT * from blog where writer_id = #{id}")
    List<Blog> GetBlogsByWriterid(Integer id);

    @Update("UPDATE blog SET commit_sum = commit_sum - 1 where id = #{id}")
    void BlogCommitSumMinusOne(Integer id);

    @Delete("DELETE FROM blog where id = #{id}")
    void DeleteBlogByid(Integer id);


    @Insert("INSERT INTO blog(writer_id, likes_num, commit_sum, blog_text, blog_topic,create_time,update_time) " +
            "values(#{writer_id},#{likes_sum},#{commit_sum},#{blog_text},#{blog_topic},#{create_time},#{update_time}) ")
    void InsertBlog(Blog blog);

    void UpdateBlog(Blog blog);

    @Select("select writer_id from blog where id = #{id}")
    Integer GetWriteridByBlogid(Integer id);
}
