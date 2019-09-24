package com.edu.victor.Controller;

import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.Service.CourseManagementService;
import com.edu.victor.Service.HWService;
import com.edu.victor.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin",method = RequestMethod.POST)
public class CourseManagementController {
    @Autowired
    CourseManagementService courseManagementService;

    /**添加课程*/
    @RequestMapping(value = "/aCourse")
    @ResponseBody
    public ResponseData addCourse(Course course, HttpServletRequest httpServletRequest) throws IncompleteInformationException, UnsupportedFileTypeException {
        Teacher teacher = (Teacher) httpServletRequest.getAttribute("Teacher");
        return courseManagementService.addCourse(course,teacher);
    }
    /**添加章节*/
    @RequestMapping(value = "/aChapter")
    @ResponseBody
    public ResponseData addChapter(Chapter chapter,HttpServletRequest httpServletRequest) throws IncompleteInformationException {
        Teacher teacher = (Teacher) httpServletRequest.getAttribute("Teacher");

        return courseManagementService.addChapter(chapter,teacher);
    }
    /**添加小节*/
    @RequestMapping(value = "/aSection")
    @ResponseBody
    public ResponseData addSection(Section section,HttpServletRequest httpServletRequest) throws UnsupportedFileTypeException, IncompleteInformationException {
        Teacher teacher = (Teacher) httpServletRequest.getAttribute("Teacher");
        return courseManagementService.addSection(section,teacher);
    }
    /**查看课程列表*/
    @RequestMapping(value = "/courses")
    @ResponseBody
    public ResponseData searchCourses(HttpServletRequest httpServletRequest){
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("Teacher");
        return courseManagementService.searchCourses(teacher.getId());
    }
    /**查看某一课程的章节和小节*/
    @RequestMapping(value = "/courseInfo/{id}")
    @ResponseBody
    public ResponseData getCourseInfo(@PathVariable int id){
        return courseManagementService.getCourseInfo(id);
    }
    /**查看某一课程下的课件*/
    @RequestMapping(value = "/coursewares/{course_id}")
    @ResponseBody
    public ResponseData getCoursewares(@PathVariable("course_id") int id){
        return courseManagementService.searchCoursewares(id);
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

    /**课件上传*/
    @RequestMapping(value = "/uploadCourseware",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData uploadCorseware(Courseware courseware,HttpServletRequest httpServletRequest) throws UnsupportedFileTypeException {
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("Teacher");
        return courseManagementService.addCourseware(courseware,teacher);
    }
/**
 * 删除文件前要先查询出url*/
    @RequestMapping("/dSection/{section_id}")
    @ResponseBody
    public ResponseData deleteSection(@PathVariable int section_id, HttpServletRequest httpServletRequest ){
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("Teacher");
        int tea_id = teacher.getId();
        return courseManagementService.deleteSection(section_id,tea_id);
    }

    /**删除课程某一章节及其下的内容*/
    @RequestMapping(value = "/dChapter/{chapter_id}")
    @ResponseBody
    public ResponseData deleteChapter(@PathVariable int chapter_id, HttpServletRequest httpServletRequest ){
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("Teacher");
        int tea_id = teacher.getId();
        return courseManagementService.deleteChapter(chapter_id,tea_id);
    }
    /**删除课程及课程下的内容，课件、视频、课程文件*/
    @RequestMapping(value = "/dCourse/{course_id}")
    @ResponseBody
    public ResponseData deleteCourse(@PathVariable int course_id, HttpServletRequest httpServletRequest ){
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("Teacher");
        int tea_id = teacher.getId();
        return courseManagementService.deleteCourse(course_id,tea_id);
    }
    /**删除某一课件*/
    @RequestMapping(value = "/dCourseware/{courseware_id}")
    @ResponseBody
    public ResponseData deleteCourseware(@PathVariable int courseware_id, HttpServletRequest httpServletRequest ){
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("Teacher");
        return courseManagementService.deleteCourseware(courseware_id,teacher.getId());
    }
    /**更改课程名、描述和图片*/
    @RequestMapping(value = "/uCourse")
    @ResponseBody
    public ResponseData updateCourse(Course course,HttpServletRequest httpServletRequest){
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("Teacher");
        course.setTea_id(teacher.getId());
        return courseManagementService.updateCourse(course);
    }
    /**更改章节名，和权重*/
    @RequestMapping(value = "/uChapter")
    @ResponseBody
    public ResponseData updateChapter(Chapter chapter,HttpServletRequest httpServletRequest){
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("Teacher");
        chapter.setTea_id(teacher.getId());
        return courseManagementService.updateChapter(chapter);
    }

    /**更改小节名，权重，视频*/
    @RequestMapping(value = "/uSection")
    @ResponseBody
    public ResponseData updateSection(Section section,HttpServletRequest httpServletRequest){
        Teacher teacher = (Teacher) httpServletRequest.getAttribute("Teacher");
        section.setTea_id(teacher.getId());
        return courseManagementService.updateSection(section);
    }

}
