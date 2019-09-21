package com.edu.victor.Controller;

import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.Service.CourseManagementService;
import com.edu.victor.domain.*;
import com.edu.victor.utils.ImageUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CourseManagementController {
    @Autowired
    CourseManagementService courseManagementService;
    @RequestMapping(value = "/aCourse",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addCourse(Course course, HttpServletRequest httpServletRequest) throws IncompleteInformationException, UnsupportedFileTypeException {
        Teacher teacher = (Teacher) httpServletRequest.getAttribute("Teacher");
        if(teacher.getEmail() == null || teacher.getName() == null)
            throw new IncompleteInformationException();
        String path = ImageUploadUtils.saveImage(course.getPic(),teacher.getId()+"","course");
        course.setPicUrl(path);
        course.setTea_id(teacher.getId());
        course.setTea_name(teacher.getName());
        ResponseData responseData = new ResponseData();
        if(courseManagementService.addCourse(course))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }
    @RequestMapping(value = "/aChapter",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addChapter(Chapter chapter){
        ResponseData responseData = new ResponseData();
        if(courseManagementService.addChapter(chapter))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }
    @RequestMapping(value = "/aSection",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addSection(Section section) throws UnsupportedFileTypeException {
        ResponseData responseData = new ResponseData();
        String path = ImageUploadUtils.saveImage(section.getVideo(),section.getSuperior_id()+"","video");
        section.setUrl(path);
        if(courseManagementService.addSection(section))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }
    @RequestMapping(value = "/courses",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData searchCourses(HttpServletRequest httpServletRequest){
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("Teacher");
        List<Course> list = courseManagementService.searchCourses(teacher.getId());
        ResponseData responseData = new ResponseData();
        if(list != null) {
            responseData.setCode(200);
            responseData.setData(list);
        }else
            responseData.setCode(0);
        return responseData;
    }
}
