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
    public Boolean deleteCourse(int id) {
        return null;
    }

    @Override
    public Boolean deleteChapter(int id) {
        return null;
    }

    @Override
    public Boolean deleteSection(int id) {
        return null;
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
}
