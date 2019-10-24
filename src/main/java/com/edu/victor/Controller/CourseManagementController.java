package com.edu.victor.Controller;

import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.Service.CourseManagementService;
import com.edu.victor.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(method = RequestMethod.POST)
public class CourseManagementController {
    @Autowired
    CourseManagementService courseManagementService;

    /**添加课程*/
    @RequestMapping(value = "/course/create")
    @ResponseBody
    public ResponseData addCourse(@Valid Course course, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws IncompleteInformationException, UnsupportedFileTypeException {
        Teacher teacher = (Teacher) httpServletRequest.getAttribute("User");
        return courseManagementService.addCourse(course,teacher);
    }
    /**添加章节*/
    @RequestMapping(value = "/chapter/create")
    @ResponseBody
    public ResponseData addChapter(@Valid Chapter chapter,BindingResult bindingResult,HttpServletRequest httpServletRequest) throws IncompleteInformationException {
        Teacher teacher = (Teacher) httpServletRequest.getAttribute("User");

        return courseManagementService.addChapter(chapter,teacher);
    }
    /**添加小节*/
    @RequestMapping(value = "/section/create")
    @ResponseBody
    public ResponseData addSection(@Valid Section section, BindingResult bindingResult, HttpServletRequest httpServletRequest) throws UnsupportedFileTypeException, IncompleteInformationException {
        Teacher teacher = (Teacher) httpServletRequest.getAttribute("User");
        return courseManagementService.addSection(section,teacher);
    }
    /**查看课程列表*/
    @RequestMapping(value = "/courses")
    @ResponseBody
    public ResponseData searchCourses(HttpServletRequest httpServletRequest,Page page){
        User user = (User)httpServletRequest.getAttribute("User");
        return courseManagementService.searchCourses(page,user);
    }
    /**查看某一课程的章节和小节*/
    @RequestMapping(value = "/courseInfo/{id}")
    @ResponseBody
    public ResponseData getCourseInfo(@PathVariable int id,HttpServletRequest httpServletRequest){
        User user = (User)httpServletRequest.getAttribute("User");
        return courseManagementService.getCourseInfo(id,user);
    }
    /**查看某一课程下的课件*/
    @RequestMapping(value = "/coursewares")
    @ResponseBody
    public ResponseData getCoursewares(@RequestParam("id") int id, Page page){
        Map map = new HashMap();
        map.put("id",id);
        page.setFilter(map);
        return courseManagementService.searchCoursewares(page);
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
    public ResponseData uploadCorseware(@Valid UploadCourseware courseware,BindingResult bindingResult,HttpServletRequest httpServletRequest) throws UnsupportedFileTypeException {
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("User");
        return courseManagementService.addCourseware(courseware,teacher);
    }
/**
 * 删除文件前要先查询出url*/
    @RequestMapping("/section/delete/{section_id}")
    @ResponseBody
    public ResponseData deleteSection(@PathVariable int section_id, HttpServletRequest httpServletRequest ){
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("User");
        int tea_id = teacher.getId();
        return courseManagementService.deleteSection(section_id,tea_id);
    }

    /**删除课程某一章节及其下的内容*/
    @RequestMapping(value = "/chapter/delete/{chapter_id}")
    @ResponseBody
    public ResponseData deleteChapter(@PathVariable int chapter_id, HttpServletRequest httpServletRequest ){
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("User");
        int teaId = teacher.getId();
        return courseManagementService.deleteChapter(chapter_id,teaId);
    }
    /**删除课程及课程下的内容，课件、视频、课程文件*/
    @RequestMapping(value = "/course/delete/{course_id}")
    @ResponseBody
    public ResponseData deleteCourse(@PathVariable int course_id, HttpServletRequest httpServletRequest ){
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("User");
        int tea_id = teacher.getId();
        return courseManagementService.deleteCourse(course_id,tea_id);
    }
    /**删除某一课件*/
    @RequestMapping(value = "/courseware/delete/{courseware_id}")
    @ResponseBody
    public ResponseData deleteCourseware(@PathVariable int courseware_id, HttpServletRequest httpServletRequest ){
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("User");
        return courseManagementService.deleteCourseware(courseware_id,teacher.getId());
    }
    /**更改课程名、描述和图片*/
    @RequestMapping(value = "/course/update")
    @ResponseBody
    public ResponseData updateCourse(Course course,HttpServletRequest httpServletRequest) throws UnsupportedFileTypeException {
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("User");
        course.setTeaId(teacher.getId());
        return courseManagementService.updateCourse(course);
    }
    /**更改章节名，和权重*/
    @RequestMapping(value = "/chapter/update")
    @ResponseBody
    public ResponseData updateChapter(Chapter chapter,HttpServletRequest httpServletRequest){
        Teacher teacher = (Teacher)httpServletRequest.getAttribute("User");
        chapter.setTeaId(teacher.getId());
        return courseManagementService.updateChapter(chapter);
    }

    /**更改小节名，权重，视频*/
    @RequestMapping(value = "/section/update")
    @ResponseBody
    public ResponseData updateSection(Section section,HttpServletRequest httpServletRequest) throws UnsupportedFileTypeException {
        Teacher teacher = (Teacher) httpServletRequest.getAttribute("User");
        section.setTeaId(teacher.getId());
        return courseManagementService.updateSection(section);
    }
    @RequestMapping(value = "/course/progress")
    @ResponseBody
    public ResponseData getStuLearningProgress(Page page, String clssNum,String stuNum,String stuName,int course_id){
        Map map = new HashMap();
        map.put("classNum",clssNum);
        map.put("stuNum",stuNum);
        map.put("stuName",stuName);
        map.put("course_id",course_id);
        page.setFilter(map);
        return courseManagementService.searchStuLearningProgress(page);
    }

    /**APP端功能*/
    /**展示和查询课程*/
    @RequestMapping("/course/search")
    @ResponseBody
    public ResponseData searchCourse(@Valid CourseSearchForApp courseSearchForApp,BindingResult bindingResult, Page page,HttpServletRequest httpServletRequest){
        User user = (User)httpServletRequest.getAttribute("User");
        return courseManagementService.searchCourse(page,user,courseSearchForApp);
    }
    /**加入课程*/
    @RequestMapping("/course/join")
    @ResponseBody
    public ResponseData searchCourse(@Valid CourseJoin course,BindingResult bindingResult,HttpServletRequest httpServletRequest){
        return courseManagementService.joinCourse(course,(User)httpServletRequest.getAttribute("User"));
    }
    @RequestMapping("/course/over")
    @ResponseBody
    public ResponseData finishCourse( Integer courseId,HttpServletRequest httpServletRequest){
        User user = (User)httpServletRequest.getAttribute("User");
        return courseManagementService.closeCourse(courseId,user);
    }
    /*@RequestMapping("/course/graduate")
    @ResponseBody
    public ResponseData graduateCourse(@NotNull @ModelAttribute Integer courseId, HttpServletRequest httpServletRequest)
    {
        User user = (User)httpServletRequest.getAttribute("User");
        return courseManagementService.graduate(courseId,user);
    }*/


}
