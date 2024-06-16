package com.Myblog.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface LikeMapper {

    @Select("SELECT blog_id from `like` where user_id = #{user_id}")
    List<Integer> GetLikeBlogIds(Integer user_id);

    @Delete("DELETE from `like` where blog_id = #{blog_id} and user_id = #{uid}")
    void DeleteLikeByBlogid(Integer blog_id,Integer uid);

    @Insert("insert into `like`(user_id, blog_id) VALUES (#{id},#{blogId})")
    void InsertLike(Integer blogId, Integer id);

    @Select("select user_id from `like` where blog_id = #{blogId}")
    Integer GetUseridbyBlogid(Integer blogId);
}
