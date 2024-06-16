package com.Myblog.mapper;

import com.Myblog.pojo.Commit;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

@Mapper
public interface CommitMapper {



    @Select("SELECT id from commit where blog_id = #{blog_id}")
    List<Integer> getMajorcommitIds(Integer blog_id);

    @Select("SELECT id from commit where father_id = #{father_id}")
    List<Integer> getSubcommitIds(Integer father_id);

    @Select("SELECT commit_text from commit where id = #{id}")
    String getCommittextById(Integer id);



    @Insert("Insert into commit(father_id,blog_id,commiter_id,commit_text,create_time)" +
            "values(#{father_id},#{blog_id},#{commiter_id},#{commit_text},#{create_time})")
    void InsertCommit(Commit commit);

    @Delete("DELETE from commit where id = #{id} ")
    void DeleteCommitByid(Integer id);

    @Select("select * from commit where id = #{id}")
    Commit GetCommitByid(Integer id);

    @Select("SELECT blog_id from commit where id = #{id}")
    Integer getBlogidByCommitId(Integer id);

    @Select("SELECT id from commit where commiter_id = #{id}")
    List<Integer> GetIdsByUserid(Integer id);


    @Select("select commiter_id from commit where id = #{id}")
    Integer getcommiteridByCommitid(Integer id);
}
