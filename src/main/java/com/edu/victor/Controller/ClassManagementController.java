package com.edu.victor.Controller;

import com.edu.victor.Service.ClassManagementService;
import com.edu.victor.domain.Page;
import com.edu.victor.domain.ResponseData;
import com.edu.victor.domain.TeachingClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/class",method = RequestMethod.POST)
public class ClassManagementController {
    @Autowired
    ClassManagementService  classManagementService;
    /**创建*/
    @RequestMapping("/create")
    @ResponseBody
    public ResponseData createClass(TeachingClass teachingClass){
        return classManagementService.createClass(teachingClass);
    }
    /**更新*/
    @RequestMapping("/update")
    @ResponseBody
    public ResponseData updateClassInfo(TeachingClass teachingClass){
        return classManagementService.updateClass(teachingClass);
    }
    /**删除*/
    @RequestMapping("/delete")
    @ResponseBody
    public ResponseData deleteClass(int id){
        return classManagementService.deleteClass(id);
    }
    /**展示班级*/
    @RequestMapping("/show")
    @ResponseBody
    public ResponseData showClass(Page page){
        return classManagementService.showClass(page);
    }
}
