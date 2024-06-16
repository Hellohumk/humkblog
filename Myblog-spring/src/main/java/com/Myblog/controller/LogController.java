package com.Myblog.controller;

import com.Myblog.pojo.Result;
import com.Myblog.pojo.User;
import com.Myblog.service.PersonService;
import com.Myblog.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

//跨域解决
@CrossOrigin
@Slf4j
@RestController
public class LogController {
    @Autowired
    private PersonService personService;

    //只拿参数还是emp都可以，但你返回肯定是按找标准返回
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        log.info("sign: {}",user);
        User e = personService.login(user);

        //gaizao
        if (e != null) {//success
            //这就是为什么我们之前要求上面返回一个对象而不是单个的对比属性
            Map<String, Object> claims = new HashMap<>();
            claims.put("id",e.getId());
            claims.put("user_name",e.getUser_name());
            String jwt = JwtUtils.generateJwt(claims);//jwt 包含了当前登录的员工信息
            return Result.success(jwt);
        }
        else
            return Result.error("Not login");

    }
}