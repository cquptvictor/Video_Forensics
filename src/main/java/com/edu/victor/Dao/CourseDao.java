package com.edu.victor.Dao;

import com.edu.victor.domain.*;

import java.util.List;

public interface CourseDao {
    Boolean addCourse(Course course);
    Boolean addChapter(Chapter chapter);
    Boolean addSection(Section section);
    Boolean addCourseware(Courseware courseware);
    List<Course> searchCourses(Integer id);
    /*List<Chapter> searchChapter(Integer id);
    List<Section> searchSection(Integer id);*/
   CourseDto getCourseInfo(int id);
}
