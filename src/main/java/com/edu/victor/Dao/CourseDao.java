package com.edu.victor.Dao;

import com.edu.victor.domain.*;

import java.util.List;

public interface CourseDao {
    Boolean addCourse(Course course);
    Boolean addChapter(Chapter chapter);
    Boolean addSection(Section section);
    Boolean addCourseware(Courseware courseware);

    Page<Course> searchCoursesByPage(Page page);
    Page<Courseware> searchCoursewareByPage(Page page);
    /*List<Chapter> searchChapter(Integer id);
    List<Section> searchSection(Integer id);*/
   CourseDtoForSpecific getCourseInfo(int id);

   Boolean deleteSection(int section_id, int tea_id);
   Boolean deleteChapter(int chapter_id, int tea_id);
   Boolean deleteCourse(int course_id, int tea_id);
   Boolean deleteCourseware(int courseware_id,int tea_id);

   String getCoursewareUrl(int id);
   String getCourseImageUrl(int id);
   List<String> getCoursewareUrlByCourse(int id);
   List<String> getSectionUrlByChapter(int chapter_id);
   List<String> getSectionUrlByCourse(int course_id);

   Boolean updateCourseInfo(Course course);
   Boolean updateChapterInfo(Chapter chapter);
   Boolean updateSectionInfo(Section section);

}
