package com.edu.victor.Controller;

import com.edu.victor.Service.LoginService;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("/admin")
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login",method = RequestMethod.PATCH)
    public ResponseData login(Teacher teacher){
        ResponseData responseData = new ResponseData();
        if(loginService.login(teacher)){
            responseData.setCode(200);
            responseData.setMessage("login Successful");
            responseData.setData(teacher);
        }else{
            responseData.setCode(0);
            responseData.setMessage("login Failed");
        }
        return responseData;
    }
}
