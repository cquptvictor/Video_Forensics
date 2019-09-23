package com.edu.victor.Service;

import com.edu.victor.Exception.IncompleteInformationException;
import com.edu.victor.Exception.UnsupportedFileTypeException;
import com.edu.victor.domain.*;

import java.util.List;


public interface CourseManagementService {
    ResponseData addCourse(Course course, Teacher teacher) throws IncompleteInformationException, UnsupportedFileTypeException;
    ResponseData addChapter(Chapter chapter, Teacher teacher) throws IncompleteInformationException;
    ResponseData addSection(Section section,Teacher teacher) throws UnsupportedFileTypeException, IncompleteInformationException;
    ResponseData addCourseware(Courseware courseware, Teacher teacher) throws UnsupportedFileTypeException;
    ResponseData searchCourses(int id);
    ResponseData searchCoursewares(int id);
  /*  List<Chapter> searchChapter(int id);
    List<Section> searchSection(int id);
    String searchCoursewareUrl(int id);
    String searchSection(int id);
    List<String> searchSectionByChapter(int id );
    List<String> searchSectionByCourse(int course_id);*/
    ResponseData getCourseInfo(int id);
    ResponseData deleteSection(int section_id, int tea_id);
    ResponseData deleteChapter(int id,int tea_id);
    ResponseData deleteCourse(int id,int tea_id);
    ResponseData deleteCourseware(int id, int tea_id);
    ResponseData updateCourse(Course course);
    ResponseData updateChapter(Chapter chapter);
    ResponseData updateSection(Section section);

}
