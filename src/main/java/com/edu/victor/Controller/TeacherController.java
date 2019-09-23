package com.edu.victor.Controller;

import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.Service.TeacherService;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.Teacher;
import com.edu.victor.utils.UploadUtils;
import com.edu.victor.utils.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/admin",method = RequestMethod.POST)
@Controller
public class TeacherController {
    @Autowired
    TeacherService loginService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public ResponseData login(Teacher teacher){

        return loginService.login(teacher);
    }

    @RequestMapping(value = "/uInfo")
    @ResponseBody
    public ResponseData updateInfo(Teacher teacher, HttpServletRequest httpServletRequest) throws UnsupportedFileTypeException {
        Teacher teacher1 = (Teacher)httpServletRequest.getAttribute("Teacher");
        teacher.setId(teacher1.getId());
        return loginService.updateInfo(teacher);

    }
  /*  @RequestMapping("/uAvatar")
    @ResponseBody
    public ResponseData updateAvatar(MultipartFile avatar,HttpServletRequest httpServletRequest) throws UnsupportedFileTypeException {
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("Teacher");
        teacher.setAvatarFile(avatar);
        return loginService.updateAvatar(teacher);
    }*/
}
