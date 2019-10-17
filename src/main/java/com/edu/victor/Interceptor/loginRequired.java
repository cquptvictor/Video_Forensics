package com.edu.victor.Interceptor;


import com.edu.victor.Exception.NotAuthorizedException;
import com.edu.victor.domain.Student;
import com.edu.victor.domain.Teacher;
import com.edu.victor.domain.User;
import com.edu.victor.utils.JWT;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**用户登录拦截*/
public class loginRequired implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        User user = null;
        String token = httpServletRequest.getParameter("token");
        try {/**Teacher必须在前*/
             user = JWT.unsign(token, Teacher.class);
             user.setIsTeacher("1");
        }catch (JsonMappingException | NullPointerException e){
            user = JWT.unsign(token,Student.class);
            user.setIsTeacher("0");
        }
        if(!JWT.authBlackList(user,token))
            throw new NotAuthorizedException();
        /**转换为对应类型*/
        if(user == null) {
            throw new NotAuthorizedException();
        }else {
            httpServletRequest.setAttribute("User", user);

        }/* if(user instanceof Teacher){
            httpServletRequest.setAttribute("Teacher",user);
        }else if(user instanceof Student){
            httpServletRequest.setAttribute("Student",user);
        }else
            return false;
*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}
