package com.edu.victor.Controller;

import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.Service.UserService;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.Student;
import com.edu.victor.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequestMapping(method = RequestMethod.POST)
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public ResponseData login(Teacher teacher) {

        return userService.login(teacher);
    }
    /**更新老师信息*/
    @RequestMapping(value = "/uInfo/teacher")
    @ResponseBody
    public ResponseData updateTeacherInfo(Teacher teacher, HttpServletRequest httpServletRequest) throws UnsupportedFileTypeException {
        Teacher teacher1 = (Teacher) httpServletRequest.getAttribute("Teacher");
        teacher.setId(teacher1.getId());
        return userService.updateTeaInfo(teacher);

    }
    /**更新学生信息*/
    @RequestMapping(value = "/uInfo/stu")
    @ResponseBody
    public ResponseData updateStudentInfo(Student student, HttpServletRequest httpServletRequest) throws UnsupportedFileTypeException {
        Student student1 = (Student)httpServletRequest.getAttribute("Student");
        student.setId(student1.getId());
        return userService.updateStuInfo(student);
    }
    /**获取学生或老师的个人信息*/
    @RequestMapping(value = "/info")
    @ResponseBody
    public ResponseData getInfo(HttpServletRequest httpServletRequest) {
        if (httpServletRequest.getAttribute("Teacher") != null) {
            Teacher teacher = (Teacher) httpServletRequest.getAttribute("Teacher");
            return userService.getTeaInfo(teacher.getId());
        } else {
            Student student = (Student) httpServletRequest.getAttribute("Student");
            return userService.getStuInfo(student.getId());
        }

    }
    /**更新学生或老师的头像*/
    @RequestMapping("/uAvatar")
    @ResponseBody
    public ResponseData updateAvatar(MultipartFile avatar, HttpServletRequest httpServletRequest) throws UnsupportedFileTypeException, IOException {
        if (httpServletRequest.getAttribute("Teacher") != null) {
            Teacher teacher = (Teacher) httpServletRequest.getAttribute("Teacher");
            return userService.updateAvatar(avatar, teacher, true);
        } else {
            Student student = (Student) httpServletRequest.getAttribute("Student");
            return userService.updateAvatar(avatar, student, false);
        }
    }
}