package com.edu.victor.Dao;

import com.edu.victor.domain.*;

import java.util.List;

public interface CourseDao {
    Boolean addCourse(Course course);
    Boolean addChapter(Chapter chapter);
    Boolean addSection(Section section);
    Boolean addCourseware(Courseware courseware);

    List<Course> searchCourses(Integer id);
    List<Courseware> searchCourseware(Integer id);
    /*List<Chapter> searchChapter(Integer id);
    List<Section> searchSection(Integer id);*/
   CourseDto getCourseInfo(int id);

   Boolean deleteSection(int section_id, int tea_id);
   Boolean deleteChapter(int chapter_id, int tea_id);
   Boolean deleteCourse(int course_id, int tea_id);
   Boolean deleteCourseware(int courseware_id,int tea_id);

   String getCoursewareUrl(int id);
   String getCourseImgageUrl(int id);
   String getSectionUrl(int id);
   List<String> getCoursewareUrlByCourse(int id);
   List<String> getSectionUrlByChapter(int chapter_id);
   List<String> getSectionUrlByCourse(int course_id);
}
