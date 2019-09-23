package com.edu.victor.Service.impl;

import com.edu.victor.Dao.CourseDao;
import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.Service.CourseManagementService;
import com.edu.victor.Service.TeacherService;
import com.edu.victor.domain.*;
import com.edu.victor.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseManagementServiceImpl implements CourseManagementService {
    @Autowired
    CourseDao courseDao;
    @Autowired
    TeacherService teacherService;
    @Override
    public ResponseData addCourse(Course course, Teacher teacher) throws IncompleteInformationException, UnsupportedFileTypeException {
        /**验证用户信息是否完整*/
        teacherService.isCompleted(teacher);
        String path = UploadUtils.saveImage(course.getPic(),teacher.getId()+"","courseImage");
        course.setPicUrl(path);
        course.setTea_id(teacher.getId());
        course.setTea_name(teacher.getName());
        ResponseData responseData = new ResponseData();
        if(courseDao.addCourse(course))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }

    @Override
    public ResponseData addChapter(Chapter chapter, Teacher teacher) throws IncompleteInformationException {
        teacher = teacherService.isCompleted(teacher);
        ResponseData responseData = new ResponseData();
        chapter.setTea_id(teacher.getId());
        if(courseDao.addChapter(chapter))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;

    }

    @Override
    public ResponseData addSection(Section section, Teacher teacher) throws UnsupportedFileTypeException, IncompleteInformationException {
        teacher = teacherService.isCompleted(teacher);
        ResponseData responseData = new ResponseData();
        String path = UploadUtils.saveImage(section.getVideo(),section.getSuperior_id()+"","video");
        section.setUrl(path);
        section.setTea_id(teacher.getId());
        if(courseDao.addSection(section))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }

    @Override
    public ResponseData searchCourses(int id) {
        List<Course> list = courseDao.searchCourses(id);
        ResponseData responseData = new ResponseData();
        if(list != null) {
            responseData.setCode(200);
            responseData.setData(list);
        }else
            responseData.setCode(0);
        return responseData;
    }

  /*  @Override
    public List<Chapter> searchChapter(int id) {
        return courseDao.searchChapter(id);

    }

    @Override
    public List<Section> searchSection(int id) {
        return courseDao.searchSection(id);
    }
*/
    @Override
    public ResponseData addCourseware(Courseware courseware, Teacher teacher) throws UnsupportedFileTypeException {
        String path = UploadUtils.saveImage(courseware.getFile(),courseware.getSuperior_id()+"","courseware");
        courseware.setUrl(path);
        courseware.setTea_id(teacher.getId());
        ResponseData responseData = new ResponseData();
        if(courseDao.addCourseware(courseware))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }

    @Override
    public ResponseData getCourseInfo(int id) {
        ResponseData responseData = new ResponseData(200);
        responseData.setData(courseDao.getCourseInfo(id));
        return responseData;
    }

    @Override
    public ResponseData searchCoursewares(int id) {
        ResponseData responseData = new ResponseData(20);
        responseData.setData(courseDao.searchCourseware(id));
        return responseData;
    }
    /**删除小节*/
    @Override
    public ResponseData deleteSection(int section_id, int tea_id) {
        ResponseData responseData = new ResponseData();
        String url = courseDao.getSectionUrl(section_id);
        if(courseDao.deleteSection(section_id,tea_id)) {
            responseData.setCode(200);
            UploadUtils.deleteFile(url,"video");
        }else
            responseData.setCode(0);
        return responseData;
    }
    /**删除章节*/
    @Override
    public ResponseData deleteChapter(int id, int tea_id) {
        ResponseData responseData = new ResponseData();
        List<String> urls = courseDao.getSectionUrlByChapter(id);
        if(courseDao.deleteChapter(id,tea_id)) {
            responseData.setCode(200);
            UploadUtils.deleteFile(urls,"video");
        }else
            responseData.setCode(0);
        return responseData;
    }
    /**删除课程不但要删除下属的章节和小节，还要删除课件以及课程图片*/
    @Override
    public ResponseData deleteCourse(int id,int tea_id) {
        ResponseData responseData = new ResponseData();
        String imageUrl = courseDao.getCourseImgageUrl(id);
        List<String> sectionUrls = courseDao.getSectionUrlByChapter(id);
        List<String> coursewareUrls= courseDao.getCoursewareUrlByCourse(id);

        if(courseDao.deleteCourse(id,tea_id)) {
            responseData.setCode(200);
            UploadUtils.deleteFile(imageUrl,"courseImage");
            UploadUtils.deleteFile(sectionUrls,"video");
            UploadUtils.deleteFile(coursewareUrls,"courseware");
        }else
            responseData.setCode(0);
        return responseData;
    }


    @Override
    public ResponseData deleteCourseware(int id, int tea_id) {
        ResponseData responseData = new ResponseData();
        String url = courseDao.getCoursewareUrl(id);
        if(courseDao.deleteCourseware(id,tea_id)) {
            responseData.setCode(200);
            UploadUtils.deleteFile(url,"courseware");
        }else
            responseData.setCode(0);
        return responseData;
    }
/*
    @Override
    public String searchCoursewareUrl(int id) {
        return courseDao.getCoursewareUrl(id);
    }

    @Override
    public String searchSection(int id) {
        return courseDao.getSectionUrl(id);
    }
    /**
     根据章节id查询出章节的下属Section的url集合
    @Override
    public List<String> searchSectionByChapter(int id) {
        return courseDao.getSectionUrlByChapter(id);
    }

    @Override
    public List<String> searchSectionByCourse(int course_id) {
        return courseDao.getSectionUrlByCourse(course_id);
    }
*/


}


