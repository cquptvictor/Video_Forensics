package com.edu.victor.Controller;

import com.edu.victor.Exception.StuNumNotFound;
import com.edu.victor.Service.StuManagementService;
import com.edu.victor.domain.Page;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.StuSearch;
import com.edu.victor.domain.Student;
import com.edu.victor.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**学生管理，增删改查和批量导入*/
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
    public ResponseData searchStu(String classNum, String stuNum, String name){
        Map<String,Object> map = new HashMap<>();
        map.put("classNum",classNum);
        map.put("username",stuNum);
        map.put("name",name);
        ResponseData responseData = new ResponseData(200);
        Page<Student> page = new Page<>();
        page.setFilter(map);
        responseData.setData(stuManagementService.searchStu(page));
        return responseData;
    }
    @RequestMapping(value = "/dStu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData deleteStu(String id){
        return null;
    }
    @RequestMapping(value = "/batchImport",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData batchImport(MultipartFile excel) throws StuNumNotFound {
        List<Student> list = ExcelUtils.excelToStudent(excel);
        int duplicateNum = stuManagementService.batchImport(list);
        ResponseData responseData = new ResponseData(200);
        String message = String.format("一共导入%d个，失败%d个",list.size(),duplicateNum);
        responseData.setMessage(message);
        return responseData;
    }
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    @ResponseBody
    public List<Student> test(){
       return stuManagementService.test();
    }
}
