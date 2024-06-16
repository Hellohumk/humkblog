package com.Myblog.service.impl;

import com.Myblog.mapper.BlogMapper;
import com.Myblog.mapper.CommitMapper;
import com.Myblog.mapper.LikeMapper;
import com.Myblog.mapper.UserMapper;
import com.Myblog.pojo.Blog;
import com.Myblog.pojo.Commit;
import com.Myblog.pojo.Result;
import com.Myblog.pojo.User;
import com.Myblog.service.BlogService;
import com.Myblog.service.PersonService;
import com.Myblog.utils.JwtUtils;
import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private CommitMapper commitMapper;

    //
    @Autowired
    private BlogService blogService;

    @Override
    public User login(User user){
        return userMapper.getByusernameandpassword(user);

    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void DeleteLike(Integer blog_id){
        //拿到用户id
        Integer uid = JwtUtils.getUserId();

        //删除对应id的like
        likeMapper.DeleteLikeByBlogid(blog_id,uid);
        //还是得拿id
        if(uid.equals(likeMapper.GetUseridbyBlogid(blog_id))){
            //更新为空情况，说白了删的不是自己的
            //对应blog——id并使该blog下的like_sum--
            blogMapper.LikeSumMinusOneByBlogid(blog_id);
        }


    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void SaveLike(Integer blogId){
        Integer id = JwtUtils.getUserId();
        //插入对应id的Like
        likeMapper.InsertLike(blogId,id);
        //blog下Like——sum++
        blogMapper.LikeSumPlusOneByBlogid(blogId);
    }

    @Override
    public List<Blog> PersonalBlogsList(){
        Integer id = JwtUtils.getUserId();
        //查询对应ids blogs
        return blogMapper.GetBlogsByWriterid(id);

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer DeleteBlog(Integer id){
        Integer uid = JwtUtils.getUserId();

        //1.只可以删除自己的
        Integer writerid = blogMapper.GetWriteridByBlogid(id);
        if(!writerid.equals(uid)) {
            //不是自己的
            return 0;
        }

        //对应user的blog_sum--
        userMapper.UserBlogSumMinusOne(uid);

        //拿到所有主评论id
        List<Integer> listmajorids = commitMapper.getMajorcommitIds(id);
        List<Integer> listids = new ArrayList<>(listmajorids);//待删除ids
        //对应blog的子commit全部删除(先)
        for(Integer i : listmajorids) {//找到左右的subids
            List<Integer> listsubids = commitMapper.getSubcommitIds(i);//加入到删除名单
            listids.addAll(listsubids);
        }
        //对应blog的commit全部删除//调用另一个事务
        for(Integer i : listids) {
            blogService.DeleteCommit(i);
        }
        //删除对应blog信息
        blogMapper.DeleteBlogByid(id);
        //喜欢表删除
        likeMapper.DeleteLikeByBlogid(id,uid);
        //成功
        return 1;
    }

    @Override
    public void SaveBlog(Blog blog){
        Integer id = JwtUtils.getUserId();
        blog.setWriter_id(id);
        blog.setLikes_sum(0);
        blog.setCommit_sum(0);
        blog.setCreate_time(LocalDateTime.now());
        blog.setUpdate_time(LocalDateTime.now());

        blogMapper.InsertBlog(blog);

        //user表变更
        //user blog_sum++
        userMapper.UserBlogSumPlusOne(id);
        //user exp+100\
        userMapper.UserExpUp(id);
    }

    @Override
    public Integer UpdateBlog(Blog blog){
        Integer uid = JwtUtils.getUserId();
        //1.只可以更新自己的
        Integer writerid = blogMapper.GetWriteridByBlogid(blog.getId());
        if(!writerid.equals(uid)) {
            //不是自己的
            return 0;
        }
        blog.setUpdate_time(LocalDateTime.now());
        blogMapper.UpdateBlog(blog);
        //success
        return 1;
    }

    @Override
    public User Userlist(){
        Integer id = JwtUtils.getUserId();
        return userMapper.getByid(id);
    }

    @Override
    public int UpdateUser(User user){
        Integer id = JwtUtils.getUserId();
        user.setId(id);
        return userMapper.UpdateUser(user);



    }


    @Override
    public User GetUserByid(Integer id){
        return userMapper.getByid(id);
    }

    @Override
    public List<Integer> GetPersonalCommit(){
        Integer id = JwtUtils.getUserId();
        return commitMapper.GetIdsByUserid(id);
    }






}