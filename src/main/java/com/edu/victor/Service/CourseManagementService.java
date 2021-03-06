package com.edu.victor.Service;

import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.domain.*;

import java.io.FileNotFoundException;


public interface CourseManagementService {
    ResponseData addCourse(Course course, Teacher teacher) throws IncompleteInformationException, UnsupportedFileTypeException;
    ResponseData addChapter(Chapter chapter, Teacher teacher) throws IncompleteInformationException;
    ResponseData addSection(Section section,Teacher teacher) throws UnsupportedFileTypeException, IncompleteInformationException;
    ResponseData addCourseware(UploadCourseware courseware, Teacher teacher) throws UnsupportedFileTypeException, FileNotFoundException;
    ResponseData searchCourses(Page page,User user);
    ResponseData searchCoursewares(Page page);

    ResponseData searchStuLearningProgress(Page page,StudentProgress studentProgress);
  /*  List<Chapter> searchChapter(int id);
    List<Section> searchSection(int id);
    String searchCoursewareUrl(int id);
    String searchSection(int id);
    List<String> searchSectionByChapter(int id );
    List<String> searchSectionByCourse(int course_id);*/
    ResponseData getCourseInfo(int id,User user);
    ResponseData deleteSection(int section_id, int tea_id);
    ResponseData deleteChapter(int id,int tea_id);
    ResponseData deleteCourse(int id,int tea_id);
    ResponseData deleteCourseware(int id, int tea_id);
    ResponseData updateCourse(Course course) throws UnsupportedFileTypeException;
    ResponseData updateChapter(Chapter chapter);
    ResponseData updateSection(Section section) throws UnsupportedFileTypeException;
    //APP端接口
    ResponseData searchCourse(Page page,User user,CourseSearchForApp courseSearchForApp);
    ResponseData joinCourse(CourseJoin course,User user);
   // ResponseData graduate(Integer courseId,User user);
    ResponseData closeCourse(int courseId,User user);
}
