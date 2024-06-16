package com.Myblog.controller;

import com.Myblog.pojo.Blog;

import com.Myblog.pojo.Result;
import com.Myblog.pojo.User;
import com.Myblog.service.BlogService;
import com.Myblog.service.PersonService;
import com.Myblog.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;



//跨域解决
@CrossOrigin
@Slf4j
@RestController
public class PersonController {
    @Autowired
    private AliOSSUtils aliOSSUtils;

    @Autowired
    private BlogService blogService;
    @Autowired
    private PersonService personService;
    //LIke

    /**
     * 查询个人喜欢的blogs
     * @return
     */
    @GetMapping("personal/Like")
    public Result GetPersonalLikeBlogs(){
        log.info("select all like blogs");

        List<Blog> list = blogService.Likelist();

        return Result.success(list);

    }

    /**
     * delete Like by blog_id
     * @param blog_id
     * @return
     */
    @DeleteMapping("personal/Like")
    public Result DeleteLikeByBlogid(@RequestParam("blog_id") Integer blog_id){
        log.info("Delete Like which blog_id :#{}",blog_id);

        personService.DeleteLike(blog_id);

        return Result.success("已完成babe");
    }

    /**
     * 保存Like，添加
     * @param
     * @return
     */

    @PostMapping("blog")
    public Result SaveLikeByBlogid(@RequestBody HashMap<String,Integer> map){
        Integer blog_id = map.get("blog_id");
        log.info("Save Like by Blog_id:#{}",blog_id);

        personService.SaveLike(blog_id);

        return Result.success("已完成babe");
    }

    /**
     * 查询自己创建的博客
     * @return
     */
    //blog
    @GetMapping("personal/Blog")
    public Result PersonalBlog() {
        log.info("Personal blogs get");

        List<Blog> list = personService.PersonalBlogsList();

        return Result.success(list);
    }


    @DeleteMapping("personal/Blog")
    public Result DeleteBlog(@RequestParam("id") Integer id){
        log.info("Personal blog Delete By id :#{}",id);

        Integer code = personService.DeleteBlog(id);
        if(code==0){
            //不是自己的
            return Result.error("Not access");
        }

        return Result.success("已完成babe");
    }

    @PostMapping("createblog")
    public Result SaveBlog(@RequestParam("blog_topic") String blog_topic,
                           @RequestParam("blog_text") String blog_text ) throws Exception{
        log.info("Save blog");



        //文件名oss存储问题

        //上传结束
        Blog blog = new Blog();
        blog.setBlog_topic(blog_topic);
        blog.setBlog_text(blog_text);
        personService.SaveBlog(blog);

        return Result.success("已完成babe");
    }

    @PostMapping("updateblog")
    public Result UpdateBlog(@RequestBody Blog blog){
        log.info("Update blog");

        Integer code = personService.UpdateBlog(blog);
        if(code==0){
            //不是自己的
            return Result.error("Not access");
        }

        return Result.success("已完成babe");
    }

    //user
    @GetMapping("personal/Home")
    public Result Userlist(){
        log.info("个人信息拦截");

        User user = personService.Userlist();

        return Result.success(user);
    }

    @PostMapping("updateperson")
    public Result UpdateUser(@RequestParam(value = "image",required = false)MultipartFile image,
                             @RequestParam(value = "gender",required = false) Integer gender,
                             @RequestParam(value = "user_name",required = false) String user_name) throws Exception{
        String url="";//默认为空
       if(image!=null) {
           String originalFilename = image.getOriginalFilename();

           //oss上传
           log.info("blog文件上传：{}", originalFilename);
           url = aliOSSUtils.upload(image);
           log.info("文件上传完成，访问url为：{}", url);
       }

        //上传结束

        log.info("个人信息更新");
        User user = new User();
            user.setUser_name(user_name);
            user.setGender(gender);
            user.setImage(url);
            int code = personService.UpdateUser(user);
            if(code==0){
                return Result.error("fail to save");
            }
            return Result.success("已完成babe");



    }

    @PostMapping("upload")
    public Result Upload(@RequestParam("file") MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return Result.error("文件为空");
        }
        String originalFilename = file.getOriginalFilename();

        //oss上传
        log.info("blog文件上传：{}",originalFilename);
        String url = aliOSSUtils.upload(file);
        log.info("文件上传完成，访问url为：{}",url);

        //上传结束
        return Result.success(url);
    }


    @GetMapping("/person")
    public Result GetUserById(Integer id){
        log.info("根据id查询用户信息");

        User user = personService.GetUserByid(id);

        return Result.success(user);
    }

    @GetMapping("personal/Commit")
    public Result GetPersonalCommit(){
        log.info("拿回个人评论");

        List<Integer> commitidsList = personService.GetPersonalCommit();

        return Result.success(commitidsList);
    }



}


