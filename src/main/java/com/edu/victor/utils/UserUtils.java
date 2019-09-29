package com.edu.victor.utils;

import com.edu.victor.domain.Teacher;
import com.edu.victor.domain.User;

import javax.servlet.http.HttpServletRequest;

public class UserUtils {
    public static User identifyUser(HttpServletRequest httpServletRequest){
        User user1 = (User)httpServletRequest.getAttribute("Teacher");
        User user2 = (User)httpServletRequest.getAttribute("Student");
        return user1 != null ? user1 : user2;
    }
}
