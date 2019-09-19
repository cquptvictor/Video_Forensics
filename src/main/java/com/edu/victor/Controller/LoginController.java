package com.edu.victor.Controller;

import com.edu.victor.Service.LoginService;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.Teacher;
import com.edu.victor.utils.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin")
@Controller
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData login(Teacher teacher){
        ResponseData responseData = new ResponseData();
        if(loginService.login(teacher)){
            responseData.setCode(200);
            responseData.setMessage("login Successful");
            Map map = new HashMap();
            map.put("token",JWT.sign(teacher,10000000));
            responseData.setData(map);
        }else{
            responseData.setCode(0);
            responseData.setMessage("login Failed");
        }
        return responseData;
    }
}
