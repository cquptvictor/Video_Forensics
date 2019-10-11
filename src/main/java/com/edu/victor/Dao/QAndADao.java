package com.edu.victor.Dao;

import com.edu.victor.domain.*;

import java.util.Map;

public interface QAndADao {
    Boolean addQuestion(CourseDiscussionQuestion question);
    Boolean addReply(CourseDiscussionAnswer answer);
    Page<CourseDiscussionQuestionDto> getQuestionByPage(Page page);
    ClassDiscussionQuestionDtoForSpecific getSpecificQuestion(int id);
    UserDto getUser(Map map);
    User getTargetIdByQsId(int id);
    User getTargetIdByRpId(int id);
}
