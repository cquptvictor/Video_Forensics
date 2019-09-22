package com.edu.victor.Service;

import com.edu.victor.domain.*;

import java.util.List;


public interface CourseManagementService {
    Boolean addCourse(Course course);
    Boolean addChapter(Chapter chapter);
    Boolean addSection(Section section);
    Boolean addCourseware(Courseware courseware);
    List<Course> searchCourses(int id);
    List<Courseware> searchCoursewares(int id);
  /*  List<Chapter> searchChapter(int id);
    List<Section> searchSection(int id);
*/
    String searchCoursewareUrl(int id);
    String searchSection(int id);
    List<String> searchSectionByChapter(int id );
    List<String> searchSectionByCourse(int course_id);
    CourseDto getCourseInfo(int id);
    Boolean deleteSection(int section_id, int tea_id);
    Boolean deleteChapter(int id,int tea_id);
    Boolean deleteCourse(int id,int tea_id);
    Boolean deleteCourseware(int id, int tea_id);

}
