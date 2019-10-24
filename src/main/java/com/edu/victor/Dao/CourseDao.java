package com.edu.victor.Dao;

import com.edu.victor.domain.*;

import java.util.List;
import java.util.Map;

public interface CourseDao {
    Boolean addCourse(Course course);
    Boolean addChapter(Chapter chapter);
    Boolean addSection(Section section);
    Boolean addCourseware(List<Courseware> courseware);

    Page<Course> searchCoursesByPage(Page page);
    Page<Courseware> searchCoursewareByPage(Page page);
    Page<LearningProgress> getStuLearningProgressByPage(Page page);

    CourseDtoForSpecific getCourseInfo(int id);
    CourseDtoSpecificForStu getCourseInfoForStu(Map<String,Object> map);

    Boolean deleteSection(int section_id, int tea_id);
    Boolean deleteChapter(int chapter_id, int tea_id);
    Boolean deleteCourse(int course_id, int tea_id);
    Boolean deleteCourseware(int courseware_id,int tea_id);

    String getSectionUrl(int id);
    String getCoursewareUrl(int id);
    String getCourseImageUrl(int id);

    List<String> getCoursewareUrlByCourse(int id);
    List<String> getSectionUrlByChapter(int chapter_id);
    List<String> getSectionUrlByCourse(int course_id);
    List<User> getStuByCourse(int course_id);

    Boolean updateCourseInfo(Course course);
    Boolean updateChapterInfo(Chapter chapter);
    Boolean updateSectionInfo(Section section);
    Boolean closeCourse(Map<String,Object> map);
   //APP
    Page searchAllCoursesByPageForApp(Page page);
    Integer authCourseCode(CourseJoin course);
    Boolean addStuToCourse(Map<String,Object> map);
    Page<Course> searchCoursesByPageForStu(Page page);
 /*   Boolean isSatisfied(Map<String,Object> map);
    Boolean graduate(Map<String,Object> map);*/

}
