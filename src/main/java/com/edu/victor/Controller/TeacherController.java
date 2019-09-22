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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin")
@Controller
public class TeacherController {
    @Autowired
    TeacherService loginService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData login(Teacher teacher){
        ResponseData responseData = new ResponseData();
        if(loginService.login(teacher)){
            responseData.setCode(200);
            responseData.setMessage("login Successful");
            Map map = new HashMap();
            map.put("token",JWT.sign(teacher,120000));
            responseData.setData(map);
        }else{
            responseData.setCode(0);
            responseData.setMessage("login Failed");
        }
        return responseData;
    }

    @RequestMapping(value = "/uInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData updateInfo(Teacher teacher, HttpServletRequest httpServletRequest){
        Teacher teacher1 = (Teacher)httpServletRequest.getAttribute("Teacher");
        teacher.setId(teacher1.getId());
        ResponseData responseData = new ResponseData();
        if(loginService.updateInfo(teacher))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }
    @RequestMapping("/uAvatar")
    @ResponseBody
    public ResponseData updateAvatar(MultipartFile avatar,HttpServletRequest httpServletRequest) throws UnsupportedFileTypeException {
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("Teacher");
        String path = UploadUtils.saveImage(avatar,String.valueOf(teacher.getId()),"avatar");
        teacher.setAvatar(path);
        ResponseData responseData = new ResponseData();
        if(loginService.updateInfo(teacher))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;    }
}
