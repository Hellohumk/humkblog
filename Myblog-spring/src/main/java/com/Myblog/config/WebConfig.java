package com.Myblog.config;

import com.Myblog.Intercepter.LoginIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginIntercepter loginIntercepter;

    //add...方法是像拦截什么样的信息
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginIntercepter).addPathPatterns("/**")
                .excludePathPatterns("/login");

    }

}
