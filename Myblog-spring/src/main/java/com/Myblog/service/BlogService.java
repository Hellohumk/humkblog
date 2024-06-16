package com.Myblog.service;

import com.Myblog.pojo.Blog;
import com.Myblog.pojo.Commit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface BlogService {

    List<Blog> list();

    Blog getBlogByid(Integer blogId);

    List<Integer> getMajorcommitIds(Integer blogId);

    List<Integer> getSubcommitIds(Integer fatherId);

    String getCommittextByid(Integer id);

    void SaveSubCommit(Commit commit);

    void SaveMajorCommit(Commit commit);

    Integer DeleteCommit(Integer id);

    Commit GetCommit(Integer id);

    List<Blog> Likelist();


}
