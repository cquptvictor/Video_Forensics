package com.edu.victor.Service;

import com.edu.victor.domain.Chapter;
import com.edu.victor.domain.Course;
import com.edu.victor.domain.Section;

import java.util.List;

public interface CourseManagementService {
    Boolean addCourse(Course course);
    Boolean addChapter(Chapter chapter);
    Boolean addSection(Section section);
    Boolean deleteCourse(int id);
    Boolean deleteChapter(int id);
    Boolean deleteSection(int id);
    List<Course> searchCourses(int id);
    List<Chapter> searchChapter(int id);
    List<Section> searchSection(int id);


}
