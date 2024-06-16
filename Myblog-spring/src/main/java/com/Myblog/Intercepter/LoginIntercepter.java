package com.Myblog.Intercepter;

import com.alibaba.fastjson.JSONObject;
import com.Myblog.pojo.Result;
import com.Myblog.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class LoginIntercepter implements HandlerInterceptor{


    @Override //(运行前)ture 放行，false，不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle .. ");
        //如果请求为 OPTIONS 请求，则返回 true,否则需要通过jwt验证
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())){
            System.out.println("OPTIONS请求，放行");
            return true;
        }



        //1.获取url
        String url = request.getRequestURL().toString();
        log.info("url is {} ", url);

        //2.if url has login,go!!!
        if (url.contains("login")) {
            log.info("login放行");
            return true;//quit
        }

        //3.请求头信息
        String jwt = request.getHeader("Authorization");

        //空
        if (!StringUtils.hasLength(jwt)) {
            log.info("token is kong");
            Result error = Result.error("NOT_LOGIN");
            //手动转换json(x这里相当于把类变成了JSON格式的字符串)
            //这里和ccontroller的对比我的理解是：controller中注解Mapping帮我们简化了这些操作，返回时直接把类转json
            //其实他底层输出还是调用这两个类进行拿和给（帮我们省去了解析拿什么给什么的步骤）
            String notLogin = JSONObject.toJSONString(error);//JSON格式的字符串
            response.getWriter().write(notLogin);
            return false;
        }

        //如果存在，判断对不对
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("令牌失败");
            Result error = Result.error("NOT_LOGIN");
            //手动转换json(x这里相当于把类变成了JSON格式的字符串)
            //这里和ccontroller的对比我的理解是：controller中注解Mapping帮我们简化了这些操作，返回时直接把类转json
            //其实他底层输出还是调用这两个类进行拿和给（帮我们省去了解析拿什么给什么的步骤）
            String notLogin = JSONObject.toJSONString(error);//JSON格式的字符串
            response.getWriter().write(notLogin);
            return false;

        }
        //通过前面的测式
        log.info("passed!!");
        return true;
    }

    @Override //（运行后）方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("PostHandle");
    }

    @Override //视图渲染完毕后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterhandle");
    }
}
