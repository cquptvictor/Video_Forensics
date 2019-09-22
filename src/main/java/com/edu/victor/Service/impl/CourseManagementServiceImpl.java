package com.edu.victor.Service.impl;

import com.edu.victor.Dao.CourseDao;
import com.edu.victor.Service.CourseManagementService;
import com.edu.victor.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseManagementServiceImpl implements CourseManagementService {
    @Autowired
    CourseDao courseDao;
    @Override
    public Boolean addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    @Override
    public Boolean addChapter(Chapter chapter) {
        return courseDao.addChapter(chapter);
    }

    @Override
    public Boolean addSection(Section section) {
        return courseDao.addSection(section);
    }

    @Override
    public List<Course> searchCourses(int id) {
        return courseDao.searchCourses(id);
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
    public Boolean addCourseware(Courseware courseware) {
        return courseDao.addCourseware(courseware);
    }

    @Override
    public CourseDto getCourseInfo(int id) {
        return courseDao.getCourseInfo(id);
    }

    @Override
    public List<Courseware> searchCoursewares(int id) {
        return courseDao.searchCourseware(id);
    }
    /**删除小节*/
    @Override
    public Boolean deleteSection(int section_id, int tea_id) {
        return courseDao.deleteSection(section_id, tea_id);
    }
    /**删除章节*/
    @Override
    public Boolean deleteChapter(int id, int tea_id) {
        return courseDao.deleteChapter(id,tea_id);
    }

    @Override
    public String searchCoursewareUrl(int id) {
        return courseDao.getCoursewareUrl(id);
    }

    @Override
    public String searchSection(int id) {
        return courseDao.getSectionUrl(id);
    }
    /**
     * 根据章节id查询出章节的下属Section的url集合*/
    @Override
    public List<String> searchSectionByChapter(int id) {
        return courseDao.getSectionUrlByChapter(id);
    }

    @Override
    public List<String> searchSectionByCourse(int course_id) {
        return courseDao.getSectionUrlByCourse(course_id);
    }

    @Override
    public Boolean deleteCourse(int id,int tea_id) {
        return courseDao.deleteCourse(id,tea_id);
    }

    @Override
    public Boolean deleteCourseware(int id, int tea_id) {
        return courseDao.deleteCourseware(id,tea_id);
    }
}
