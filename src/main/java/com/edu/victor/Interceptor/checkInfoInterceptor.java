package com.edu.victor.Interceptor;

import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.domain.Student;
import com.edu.victor.domain.Teacher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**检查用户信息是不是完整的*/
public class checkInfoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Student student = null;
        Teacher teacher = null;
        if((student =(Student) httpServletRequest.getAttribute("Student")) != null){
            return false;
        }else{
            teacher = (Teacher)httpServletRequest.getAttribute("Teacher");
            if(teacher.getEmail() == null || teacher.getName() == null)
                throw new IncompleteInformationException();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
