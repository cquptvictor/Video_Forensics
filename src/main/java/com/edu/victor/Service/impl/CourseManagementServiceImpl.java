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
        course.setUrl(path);
        course.setTeaId(teacher.getId());
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
        courseware.setTeaId(teacher.getId());
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
    public ResponseData searchCoursewares(Page page) {
        ResponseData responseData = new ResponseData(20);
        Page page1 = courseDao.searchCoursewareByPage(page);
        page.setPageData(page1.getPageData());
        responseData.setData(page);
        return responseData;
    }

    @Override
    public ResponseData searchStuLearningProgress(Page page) {
        Page page1 = courseDao.getStuLearningProgressByPage(page);
        page.setPageData(page1.getPageData());
        ResponseData responseData = new ResponseData(200);
        responseData.setData(page);
        return responseData;
    }

    @Override
    public ResponseData searchCourses(Page page) {
        Page page1 = courseDao.searchCoursesByPage(page);
        page.setPageData(page1.getPageData());
        ResponseData responseData = new ResponseData(200);
        responseData.setData(page);
        return responseData;
    }
    /**删除小节*/
    @Override
    public ResponseData deleteSection(int section_id, int tea_id) {
        ResponseData responseData = new ResponseData();
        String url = courseDao.getCourseImageUrl(section_id);
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
        String imageUrl = courseDao.getCourseImageUrl(id);
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
    //更新操作

    /**查询出图片路径，替换图片，然后正常更新*/
    @Override
    public ResponseData updateCourse(Course course) {
        if(courseDao.updateCourseInfo(course)) {
            if (course.getPic() != null) {
                String url = courseDao.getCourseImageUrl(course.getId());
                //提取文件名
                //String filePath = url.split(".",1)[0];
                UploadUtils.updateCourseImage(course.getPic(), url);
            }
        }
        ResponseData responseData = new ResponseData();
        if(courseDao.updateCourseInfo(course))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }

    @Override
    public ResponseData updateChapter(Chapter chapter) {
        ResponseData responseData = new ResponseData();
        if(courseDao.updateChapterInfo(chapter)){
            responseData.setCode(200);
        }else {
            responseData.setCode(0);
        }
        return responseData;
    }

    @Override
    public ResponseData updateSection(Section section) {
        ResponseData responseData = new ResponseData();
        if(courseDao.updateSectionInfo(section))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }
}


