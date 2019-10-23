package com.edu.victor.Controller;

import com.auth0.jwt.internal.org.bouncycastle.math.ec.ScaleYPointMap;
import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.Service.UserService;
import com.edu.victor.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(method = RequestMethod.POST)
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public ResponseData adminLogin(@Valid User user, BindingResult bindingResult) {
        return userService.login(user);
    }

    /**登出*/
    @RequestMapping("/logout")
    @ResponseBody
    public ResponseData logout(String token,HttpServletRequest httpServletRequest){
       User user = (User)httpServletRequest.getAttribute("User");
       return userService.logout(token,user);
    }
    /**更新老师信息*/
    @RequestMapping(value = "/uInfo/teacher")
    @ResponseBody
    public ResponseData updateTeacherInfo(Teacher teacher, HttpServletRequest httpServletRequest){
        Teacher teacher1 = (Teacher) httpServletRequest.getAttribute("User");
        teacher.setId(teacher1.getId());
        return userService.updateTeaInfo(teacher);

    }
    /**更新学生信息*/
    @RequestMapping(value = "/uInfo/student")
    @ResponseBody
    public ResponseData updateStudentInfo(Student student, HttpServletRequest httpServletRequest) {
        Student student1 = (Student)httpServletRequest.getAttribute("User");
        student.setId(student1.getId());
        return userService.updateStuInfo(student);
    }
    /**获取学生或老师的个人信息*/
    @RequestMapping(value = "/info")
    @ResponseBody
    public ResponseData getInfo(HttpServletRequest httpServletRequest) {
        User user = (User)httpServletRequest.getAttribute("User");
        return userService.getInfo(user);


    }
    /**更新学生或老师的头像*/
    @RequestMapping("/uAvatar")
    @ResponseBody
    public ResponseData updateAvatar(MultipartFile file, HttpServletRequest httpServletRequest) throws UnsupportedFileTypeException, IOException {
        User user = (User)httpServletRequest.getAttribute("User");
        return userService.updateAvatar(file,user,user.getIsTeacher().equals("1") ? true : false);
       /* if (user.getIsTeacher().equals("1")) {
            Teacher teacher = (Teacher) httpServletRequest.getAttribute("User");
            return userService.updateAvatar(avatar, teacher, true);
        } else {
            Student student = (Student) httpServletRequest.getAttribute("Student");
            return userService.updateAvatar(avatar, student, false);
        }*/
    }
    @RequestMapping("/messages")
    @ResponseBody
    public ResponseData getUserMessages(Page page, HttpServletRequest httpServletRequest){
        User user = (User)httpServletRequest.getAttribute("User");
        return userService.getMessages(page,user);
    }

    /**获取用户未读的消息数目*/
    @RequestMapping("/unreadNum")
    @ResponseBody
    public ResponseData getUnreadNum(HttpServletRequest httpServletRequest){
        User user = (User)httpServletRequest.getAttribute("User");
        return userService.getUnreadMessageNum(user);
    }
    /**标记消息为未读*/
    @RequestMapping("/markAsRead")
    @ResponseBody
    public ResponseData markAsRead(Integer[] msgUserId, HttpServletRequest httpServletRequest){
        User user = (User)httpServletRequest.getAttribute("User");
        return userService.MarkUnreadAsRead(msgUserId,user);
    }

}