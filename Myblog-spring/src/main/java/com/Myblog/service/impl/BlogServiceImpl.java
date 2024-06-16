package com.Myblog.service.impl;

import com.Myblog.mapper.BlogMapper;
import com.Myblog.mapper.CommitMapper;
import com.Myblog.mapper.LikeMapper;
import com.Myblog.mapper.UserMapper;
import com.Myblog.pojo.Blog;
import com.Myblog.pojo.Commit;
import com.Myblog.service.BlogService;
import io.micrometer.observation.annotation.Observed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Myblog.utils.JwtUtils;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private CommitMapper commitMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LikeMapper likeMapper;


    @Override
    public List<Blog> list(){
        return blogMapper.list();
    }

    @Override
    public Blog getBlogByid(Integer id){
        return blogMapper.getBlogByid(id);
    }
    @Override
    public List<Integer> getMajorcommitIds(Integer blog_id){
        return commitMapper.getMajorcommitIds(blog_id);

    }

    @Override
    public List<Integer> getSubcommitIds(Integer father_id){
        return commitMapper.getSubcommitIds(father_id);
    }

    @Override
    public String getCommittextByid(Integer id){
        return commitMapper.getCommittextById(id);

    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void SaveSubCommit(Commit commit){
        //jwt拿ID
        Integer id = JwtUtils.getUserId();
        //commit信息存储
        commit.setCreate_time(LocalDateTime.now());
        commit.setBlog_id(0);
        commit.setCommiter_id(id);
        commitMapper.InsertCommit(commit);

        //用户评论数+1
        userMapper.UserCommitSumPlusOne(id);

        //博客commit——sum+1
        //子评论不需要增加博客评论数
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void SaveMajorCommit(Commit commit){
        //jwt拿ID
        Integer id = JwtUtils.getUserId();
        //commit信息存储
        commit.setCreate_time(LocalDateTime.now());
        commit.setFather_id(0);
        commit.setCommiter_id(id);
        commitMapper.InsertCommit(commit);

        //用户评论数+1
        userMapper.UserCommitSumPlusOne(id);

        //该blog底下评论数+1
        blogMapper.BlogCommitSumPlusOne(commit.getBlog_id());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer DeleteCommit(Integer id){
        //id : commit id
        //uid : 用户自己的id
        //sid : 该评论（如果是主评论）的id
        //jwt拿ID
        Integer uid = JwtUtils.getUserId();
        //检查是否是自己的id，否则不予通过
        Integer commiterid = commitMapper.getcommiteridByCommitid(id);
        if(commiterid==null){
            //不是自己的
            return 0;
        }

        //commit信息删除
        commitMapper.DeleteCommitByid(id);
        //用户评论数-1
        userMapper.UserCommitSumMinusOne(uid);

        //blog下评论数-1
        //先拿回该blog下id
        Integer sid = commitMapper.getBlogidByCommitId(id);
        if(sid != null){
            //删
            blogMapper.BlogCommitSumMinusOne(sid);
        }
        return 1;

    }

    @Override
    public Commit GetCommit(Integer id){
        return commitMapper.GetCommitByid(id);
    }

    @Override
    public List<Blog> Likelist(){
        Integer id = JwtUtils.getUserId();
        //查询所有的喜欢的blog的id
        List<Integer> blogids = likeMapper.GetLikeBlogIds(id);
        //根据ids拿回List<Blog>
        if(blogids.isEmpty()){//喜欢为空
            return null;
        }
        return blogMapper.GetBlogsByids(blogids);

    }








}
