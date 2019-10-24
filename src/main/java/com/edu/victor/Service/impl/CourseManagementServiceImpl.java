package com.edu.victor.Service.impl;

import com.edu.victor.Dao.CourseDao;
import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.Service.CourseManagementService;
import com.edu.victor.Service.UserService;
import com.edu.victor.domain.*;
import com.edu.victor.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseManagementServiceImpl implements CourseManagementService {
    @Autowired
    CourseDao courseDao;
    @Autowired
    UserService teacherService;
    @Override
    public ResponseData addCourse(Course course, Teacher teacher) throws IncompleteInformationException, UnsupportedFileTypeException {
        /**验证用户信息是否完整*/
        teacherService.isCompleted(teacher);
        String path = FileUtils.saveImage(course.getPic(),"courseImage");
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
        chapter.setTeaId(teacher.getId());
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
        String path = FileUtils.saveImage(section.getFile(),"video");
        section.setUrl(path);
        section.setTeaId(teacher.getId());
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
    public ResponseData addCourseware(UploadCourseware courseware, Teacher teacher) throws UnsupportedFileTypeException {

        List<Courseware> coursewares = FileUtils.multiSaveCourseware(courseware.getMultipartFile(),teacher.getId(),courseware.getSuperiorId());
        ResponseData responseData = new ResponseData();

        if(courseDao.addCourseware(coursewares))
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
        ResponseData<Page> responseData = new ResponseData(200);
        Page page1 = courseDao.searchCoursewareByPage(page);
        page.setPageData(page1 != null ? page1.getPageData() : null);
        responseData.setData(page);
        return responseData;
    }

    @Override
    public ResponseData searchStuLearningProgress(Page page) {
        Page page1 = courseDao.getStuLearningProgressByPage(page);
        page.setPageData(page1 != null ? page1.getPageData() : null);
        ResponseData responseData = new ResponseData(200);
        responseData.setData(page);
        return responseData;
    }

    @Override
    public ResponseData searchCourses(Page page, User user) {
        Page page1= null;
        Map<String,Object> map = new HashMap<>();
        map.put("id",user.getId());
        page.setFilter(map);
        ResponseData responseData = new ResponseData(200);
        if(user.getIsTeacher().equals("1")){
            page1 = courseDao.searchCoursesByPage(page);
            page.setPageData(page1 != null ? page1.getPageData() : null);
            responseData.setData(page);
        }else{
            responseData.setCode(0);
        }

        return responseData;
    }
    /**删除小节*/
    @Override
    @Transactional
    public ResponseData deleteSection(int section_id, int tea_id) {
        ResponseData responseData = new ResponseData();
        String url = courseDao.getSectionUrl(section_id);
        if(courseDao.deleteSection(section_id,tea_id)) {
            responseData.setCode(200);
            FileUtils.deleteFile(url,"video");
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
            FileUtils.deleteFile(urls,"video");
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
            FileUtils.deleteFile(imageUrl,"courseImage");
            FileUtils.deleteFile(sectionUrls,"video");
            FileUtils.deleteFile(coursewareUrls,"courseware");
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
            FileUtils.deleteFile(url,"courseware");
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
    public ResponseData updateCourse(Course course) throws UnsupportedFileTypeException {
            if (course.getPic() != null) {
                String url = courseDao.getCourseImageUrl(course.getId());
                //提取文件名
                //String filePath = url.split(".",1)[0];
                FileUtils.updateCourseImage(course.getPic(), url);
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
    public ResponseData updateSection(Section section) throws UnsupportedFileTypeException {
        ResponseData responseData = new ResponseData();
        //如果更新了视频要对视频单独处理
        if(section.getFile() != null){
            String url = courseDao.getSectionUrl(section.getId());
            FileUtils.updateSectionVideo(section.getFile(),url);
        }
        if(courseDao.updateSectionInfo(section))
            responseData.setCode(200);
        else
            responseData.setCode(0);
        return responseData;
    }

    /**APP端接口*/
    @Override
    public ResponseData searchCourse(Page page, User user, String type) {
        ResponseData responseData = new ResponseData(200);

       if(type.equals("all")){
            Page page1 = courseDao.searchAllCoursesByPageForApp(page);
            page.setPageData(page1 != null ? page1.getPageData() : null);
            responseData.setData(page);
        }else if(type.equals("my")){
           Page page1 = courseDao.searchCoursesByPageForStu(page);
           page.setPageData(page1 != null ? page1.getPageData() : null);
           responseData.setData(page);
       }else
           responseData.setCode(0);
       return responseData;
    }
    /**
     * 验证课程邀请码对不对
     * 添加学生到课程
     * */
    @Override
    @Transactional
    public ResponseData joinCourse(CourseJoin course,User user) {
        ResponseData responseData = new ResponseData(200);
        if(courseDao.authCourseCode(course) == -1){
            responseData.setCode(0);
            responseData.setMessage("验证码错误");
        }else{
            Map<String,Object> map = new HashMap<>();
            map.put("courseId",course.getId());
            map.put("stuId",user.getId());
            if(courseDao.addStuToCourse(map)){
                responseData.setMessage("成功加入课程");
            }else{
                responseData.setMessage("您已加入课程");
                responseData.setCode(0);
            }
        }
        return responseData;
    }
    /**
     * 结业要先校验用户是否达到结业要求*/
   /* @Override
    public ResponseData graduate(Integer courseId, User user) {
        ResponseData responseData = new ResponseData(0);
        if(user.getIsTeacher().equals("0")) {
            Map<String,Object> map = new HashMap<>();
            map.put("courseId", courseId);
            map.put("stuId", user.getId());
            if(courseDao.graduate(map))
                responseData.setCode(200);
            else
                responseData.setMessage("您未达到结业要求");
        }
        return responseData;
    }*/
    /**老师关闭了课程之后，学生就不能再进入课程了*/
    @Override
    public ResponseData closeCourse(int courseId, User user) {
        ResponseData responseData = new ResponseData(0);
        if(user.getIsTeacher().equals("1")){
            Map<String,Object> map = new HashMap<>();
            map.put("courseId",courseId);
            map.put("teaId",user.getId());
            if(courseDao.closeCourse(map))
                responseData.setCode(200);
        }
        return responseData;
    }
}


