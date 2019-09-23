package com.edu.victor.Controller;

import com.edu.victor.Exception.StuNumNotFound;
import com.edu.victor.Service.StuManagementService;
import com.edu.victor.domain.Page;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.Student;
import com.edu.victor.utils.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**学生管理，增删改查和批量导入*/
@RequestMapping(value = "/admin",method = RequestMethod.POST)
@Controller
public class StuManagementController {
    @Autowired
    StuManagementService stuManagementService;
    @RequestMapping(value = "/aStu")
    @ResponseBody
    public ResponseData addStu(String username){
        return stuManagementService.addStu(username);
    }
    @RequestMapping(value = "/uStu")
    @ResponseBody
    public ResponseData updateStu(Student student){
        return stuManagementService.updateStu(student);
    }

    @RequestMapping(value = "/sStu")
    @ResponseBody
    public ResponseData searchStu(String classNum, String stuNum, String name, @RequestParam("page") Integer currentPage){
        Map<String,Object> map = new HashMap<>();
        map.put("classNum",classNum);
        map.put("username",stuNum);
        map.put("name",name);
        return stuManagementService.searchStu(map,currentPage);
    }
    @RequestMapping(value = "/dStu")
    @ResponseBody
    public ResponseData deleteStu(String id){
        return null;
    }
    @RequestMapping(value = "/batchImport",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData batchImport(MultipartFile excel) throws StuNumNotFound {
        return stuManagementService.batchImport(excel);
    }

}
