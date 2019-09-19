package com.edu.victor.Controller;

import com.edu.victor.Service.StuManagementService;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.StuSearch;
import com.edu.victor.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/admin")
@Controller
public class StuManagementController {
    @Autowired
    StuManagementService stuManagementService;
    @RequestMapping(value = "/aStu",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addStu(String username){
        ResponseData responseData = new ResponseData();
        if(stuManagementService.addStu(username))
        {
            responseData.setCode(200);
        }else
            responseData.setCode(0);
        return responseData;
    }
    @RequestMapping(value = "/uStu",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData updateStu(Student student){
        ResponseData responseData = new ResponseData();
        if(stuManagementService.updateStu(student))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }

    @RequestMapping(value = "/sStu",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData searchStu(StuSearch stuSearch){
        ResponseData responseData = new ResponseData(200);
        responseData.setData(stuManagementService.searchStu(stuSearch));
        return responseData;
    }
    @RequestMapping(value = "/dStu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData deleteStu(String id){
        return null;
    }
    @RequestMapping(value = "/batchImport",method = RequestMethod.POST)
    public ResponseData batchImport(MultipartFile excel){

    }
}
