package com.edu.victor.Interceptor;


import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.domain.Student;
import com.edu.victor.domain.Teacher;
import com.edu.victor.domain.User;
import com.edu.victor.utils.JWT;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**用户登录拦截*/
@Component
public class loginRequired implements HandlerInterceptor {
    @Autowired
    RedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        User user = null;
        String token = httpServletRequest.getParameter("token");

        try {/**Teacher必须在前*/
             user = JWT.unsign(token, Teacher.class);

            if(user == null) {
                throw new NotAuthorizedException();
            }
            user.setIsTeacher("1");
        }catch (JsonMappingException | NullPointerException e){
            user = JWT.unsign(token,Student.class);
            if(user == null) {
                throw new NotAuthorizedException();
            }
            //验证单设备登录
            if(!token.equals(redisTemplate.opsForValue().get("student_"+user.getId())))
                throw new NotAuthorizedException("您已在另一台设备上登录");
            user.setIsTeacher("0");
        }
        if(!JWT.authBlackList(user,token))
            throw new NotAuthorizedException();
        httpServletRequest.setAttribute("User", user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
