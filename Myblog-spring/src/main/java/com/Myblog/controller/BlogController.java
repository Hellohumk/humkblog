package com.Myblog.controller;

import com.Myblog.pojo.Blog;
import com.Myblog.pojo.Commit;
import com.Myblog.pojo.Result;
import com.Myblog.pojo.User;
import com.Myblog.service.BlogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@CrossOrigin
//log
@Slf4j
@RestController
public class BlogController {

    @Autowired
    private BlogService blogService;

    /**
     * 查询所有的blogs
     * @return
     */
    @GetMapping("/blog/Home")
    public Result list(){
        log.info("search for all blogs");

        List<Blog> blogList = blogService.list();

        return Result.success(blogList);
    }

    /**
     * 依据blog_id查询对应的blog
     * @param blog_id
     * @return
     */
    @GetMapping("/blog/Page")
    public Result getBlogByid(@RequestParam("blog_id") Integer blog_id){
        log.info("search for blog by id:{}",blog_id);

        Blog blog = blogService.getBlogByid(blog_id);

        return Result.success(blog);
    }

    //Commit 业务
    /**
     * 依据blog_id查询blog下主评论
     * @param
     * @return
     */
    @PostMapping("/CommitSet")
    public Result getMajorcommitByid(@RequestBody Map<String,Integer> map){
        Integer blog_id = map.get("blog_id");
        log.info("get major commit ids by blog id:{}",blog_id);

        List<Integer> ids = blogService.getMajorcommitIds(blog_id);

        return Result.success(ids);
    }

    /**
     * 根据father_id查询主评论下的子评论
     * @param
     * @return
     */
    @PostMapping("/blog/CommitCommon")
    public Result getSubcommitByid(@RequestBody Map<String,Integer> map){
        Integer father_id = map.get("father_id");
        log.info("get sub commit by father id:{}",father_id);

        List<Integer> ids = blogService.getSubcommitIds(father_id);

        return Result.success(ids);
    }

    /**
     * 根据id查询评论内容
     * @param id
     * @return
     */
//    @GetMapping("/Commit")
//    public Result GetCommittextByid(@RequestParam("id") Integer id){
//        log.info("search for commit-text by id:{}",id);
//
//        String commit_text = blogService.getCommittextByid(id);
//
//        return Result.success(commit_text);
//    }

    /**
     * 添加子评论
     * @param commit
     * @return
     */

    @PostMapping("/commit")
    public Result SaveSubCommit(@RequestBody Commit commit){
        log.info("Insert sub commit: #{}",commit);

        blogService.SaveSubCommit(commit);

        return Result.success("已完成babe");
    }

    /**
     * 添加主评论
     * @param commit
     * @return
     */
    @PostMapping("/blog/Page")
    public Result SaveMajorCommit(@RequestBody Commit commit){
        log.info("Insert major commit: #{}",commit);

        blogService.SaveMajorCommit(commit);

        return Result.success("已完成babe");
    }

    @DeleteMapping("commit")
    public Result DeleteCommitByid(@RequestParam("id") Integer id){
        log.info("Delete commit which id :#{}",id);

        Integer code  = blogService.DeleteCommit(id);
        if(code==0){
            //删的不是自己的
            return Result.error("Not access");
        }
        return Result.success("已完成babe");
    }

    /**
     * 依据id拿评论
     * @param id
     * @return
     */

    @GetMapping("commit")
    public Result GetCommitByid(@RequestParam("id") Integer id){
        log.info("get commit which id is:#{}",id);

        Commit commit = blogService.GetCommit(id);

        return Result.success(commit);

    }






}
