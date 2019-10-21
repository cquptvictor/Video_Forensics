package com.edu.victor.Dao;

import com.edu.victor.domain.*;

import java.util.Map;


public interface ClassDao {
    Boolean create(TeachingClass teachingClass);
    Boolean update(TeachingClass teachingClass);
    Boolean delete(int id);
    Page getClassByPage(Page page);
    Boolean addQuestion(ClassDiscussionQuestion question);
    Boolean addReply(ClassDiscussionAnswer answer);
    Page<ClassDiscussionQuestionDto> getQuestionByPage(Page page);
    ClassDiscussionQuestionDtoForSpecific getSpecificQuestion(int id);
    UserDto getUser(Map map);
    User getTargetIdByQsId(int id);
    User getTargetIdByRpId(int id);

    //APP
    Integer getStudentClass(Map<String,Object> map);
    Boolean updateStudentClass(Map<String,Object> map);
    Boolean addStudentToClass(Map<String,Object> map);
}


