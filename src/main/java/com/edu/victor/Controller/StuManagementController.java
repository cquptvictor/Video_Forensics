package com.edu.victor.Controller;

import com.edu.victor.Exception.StuNumNotFound;
import com.edu.victor.Service.StuManagementService;
import com.edu.victor.domain.Page;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.Map;

/**学生管理，增删改查和批量导入*/
@RequestMapping(method = RequestMethod.POST)
@Controller
public class StuManagementController {
    @Autowired
    StuManagementService stuManagementService;
    @RequestMapping(value = "/student/add")
    @ResponseBody
    public ResponseData addStu(String username){
        return stuManagementService.addStu(username);
    }
    @RequestMapping(value = "/student/update")
    @ResponseBody
    public ResponseData updateStu(Student student){
        return stuManagementService.updateStu(student);
    }

    @RequestMapping(value = "/students")
    @ResponseBody
    public ResponseData searchStu(String classNum, String stuNum, String stuName,Page page){
        Map<String,Object> map = new HashMap<>();
        map.put("classNum",classNum);
        map.put("stuNum",stuNum);
        map.put("stuName",stuName);
        page.setFilter(map);
        return stuManagementService.searchStu(page);
    }
    @RequestMapping(value = "/student/delete")
    @ResponseBody
    public ResponseData deleteStu(String id){
        return null;
    }
    @RequestMapping(value = "/batchImport",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData batchImport(MultipartFile file) throws StuNumNotFound {
        return stuManagementService.batchImport(file);
    }
}
