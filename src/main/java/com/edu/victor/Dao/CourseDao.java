package com.edu.victor.Dao;

import com.edu.victor.domain.Chapter;
import com.edu.victor.domain.Course;
import com.edu.victor.domain.Section;

import java.util.List;

public interface CourseDao {
    Boolean addCourse(Course course);
    Boolean addChapter(Chapter chapter);
    Boolean addSection(Section section);
    List<Course> searchCourses(Integer id);
    List<Chapter> searchChapter(Integer id);
    List<Section> searchSection(Integer id);
}
