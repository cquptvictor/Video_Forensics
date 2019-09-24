package com.edu.victor.Interceptor;


import com.edu.victor.domain.Student;
import com.edu.victor.domain.Teacher;
import com.edu.victor.domain.User;
import com.edu.victor.utils.JWT;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**用户登录拦截*/
public class loginRequired implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Teacher teacher = null;
        Student student = null;
        String token = httpServletRequest.getParameter("token");
        Teacher user = JWT.unsign(token, Teacher.class);
        /**转换为对应类型*/
        if(user instanceof Teacher){
            teacher = (Teacher) user;
            httpServletRequest.setAttribute("Teacher",teacher);
        }/*else if(user instanceof Student){
            student = (Student) user;
            httpServletRequest.setAttribute("Student",student);
        }else
            return true;
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
