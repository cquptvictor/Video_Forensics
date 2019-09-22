package com.edu.victor.Controller;

import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.Service.CourseManagementService;
import com.edu.victor.domain.*;
import com.edu.victor.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
        String path = UploadUtils.saveImage(course.getPic(),teacher.getId()+"","course");
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
    public ResponseData addChapter(Chapter chapter,HttpServletRequest httpServletRequest) throws IncompleteInformationException {
        Teacher teacher = (Teacher) httpServletRequest.getAttribute("Teacher");
        if(teacher.getEmail() == null || teacher.getName() == null)
            throw new IncompleteInformationException();
        ResponseData responseData = new ResponseData();
        chapter.setTea_id(teacher.getId());
        if(courseManagementService.addChapter(chapter))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }
    @RequestMapping(value = "/aSection",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData addSection(Section section,HttpServletRequest httpServletRequest) throws UnsupportedFileTypeException, IncompleteInformationException {
        Teacher teacher = (Teacher) httpServletRequest.getAttribute("Teacher");
        if(teacher.getEmail() == null || teacher.getName() == null)
            throw new IncompleteInformationException();
        ResponseData responseData = new ResponseData();
        String path = UploadUtils.saveImage(section.getVideo(),section.getSuperior_id()+"","video");
        section.setUrl(path);
        section.setTea_id(teacher.getId());
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
    @RequestMapping(value = "/courseInfo/{id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData getCourseInfo(@PathVariable int id){
        ResponseData responseData = new ResponseData(200);
        responseData.setData(courseManagementService.getCourseInfo(id));
        return responseData;
    }

    @RequestMapping(value = "/coursewares/{id}")
    @ResponseBody
    public ResponseData getCoursewares(@PathVariable int id){
        ResponseData responseData = new ResponseData(20);
        responseData.setData(courseManagementService.searchCoursewares(id));
        return responseData;
    }
    /*@RequestMapping(value = "/chapter/{course_id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData getChapter(@PathVariable int course_id){
        ResponseData responseData = new ResponseData();
        List<Chapter> list = courseManagementService.searchChapter(course_id);
        if(list != null) {
            responseData.setCode(200);
            responseData.setData(list);
        }else{
            responseData.setCode(0);
        }
        return responseData;
    }
    @RequestMapping(value = "/section/{chapter_id}",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData getSection(@PathVariable int chapter_id){
        ResponseData responseData = new ResponseData();
        List<Section> list = courseManagementService.searchSection(chapter_id);
        if(list != null) {
            responseData.setCode(200);
            responseData.setData(list);
        }else{
            responseData.setCode(0);
        }
        return responseData;
    }*/
    @RequestMapping(value = "/uploadCourseware",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData uploadCorseware(Courseware courseware,HttpServletRequest httpServletRequest) throws UnsupportedFileTypeException {
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("Teacher");
        String path = UploadUtils.saveImage(courseware.getFile(),courseware.getSuperior_id()+"","courseware");
        courseware.setUrl(path);
        courseware.setTea_id(teacher.getId());
        ResponseData responseData = new ResponseData();
        if(courseManagementService.addCourseware(courseware))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }
/**
 * 删除文件前要先查询出url*/
    @RequestMapping("/dSection/{section_id}")
    @ResponseBody
    public ResponseData deleteSection(@PathVariable int section_id, HttpServletRequest httpServletRequest ){
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("Teacher");
        int tea_id = teacher.getId();
        ResponseData responseData = new ResponseData();
        String url = courseManagementService.searchSection(section_id);
        if(courseManagementService.deleteSection(section_id,tea_id)) {
            responseData.setCode(200);
            UploadUtils.deleteFile(url,"video");
        }else
            responseData.setCode(0);
        return responseData;
    }
    @RequestMapping(value = "/dChapter/{chapter_id}")
    @ResponseBody
    public ResponseData deleteChapter(@PathVariable int chapter_id, HttpServletRequest httpServletRequest ){
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("Teacher");
        int tea_id = teacher.getId();
        ResponseData responseData = new ResponseData();
        List<String> urls = courseManagementService.searchSectionByChapter(chapter_id);

        if(courseManagementService.deleteChapter(chapter_id,tea_id)) {
            responseData.setCode(200);
            UploadUtils.deleteFile(urls,"video");
        }else
            responseData.setCode(0);
        return responseData;
    }
    @RequestMapping(value = "/dCourse/{course_id}")
    @ResponseBody
    public ResponseData deleteCourse(@PathVariable int course_id, HttpServletRequest httpServletRequest ){
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("Teacher");
        int tea_id = teacher.getId();
        ResponseData responseData = new ResponseData();
        List<String> urls = courseManagementService.searchSectionByCourse(course_id);

        if(courseManagementService.deleteCourse(course_id,tea_id)) {
            responseData.setCode(200);
            UploadUtils.deleteFile(urls,"video");
        }else
            responseData.setCode(0);
        return responseData;
    }
    @RequestMapping(value = "/dCourseware/{courseware_id}")
    @ResponseBody
    public ResponseData deleteCourseware(@PathVariable int courseware_id, HttpServletRequest httpServletRequest ){
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("Teacher");
        int tea_id = teacher.getId();
        ResponseData responseData = new ResponseData();
        String url = courseManagementService.searchCoursewareUrl(courseware_id);
        if(courseManagementService.deleteCourseware(courseware_id,tea_id)) {
            responseData.setCode(200);
            UploadUtils.deleteFile(url,"courseware");
        }else
            responseData.setCode(0);
        return responseData;
    }
}
