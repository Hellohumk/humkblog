package com.Myblog.exception;

import com.Myblog.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)//捕获所有
    public Result Ex(Exception ex){
        ex.printStackTrace();
        return Result.error("有错误，你个2b");
    }
}
