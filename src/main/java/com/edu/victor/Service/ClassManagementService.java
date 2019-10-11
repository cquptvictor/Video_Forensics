package com.edu.victor.Service;

import com.edu.victor.domain.*;

public interface ClassManagementService {
    ResponseData createClass(TeachingClass teachingClass);
    ResponseData updateClass(TeachingClass teachingClass);
    ResponseData deleteClass(int id);
    ResponseData showClass(Page<TeachingClass> page);
    ResponseData addQustion(ClassDiscussionQuestion classDiscussionQuestion, User user);
    ResponseData relpy(ClassDiscussionAnswer answer, User user);
    ResponseData getQuestionList(Page page,int id);
    ResponseData getSpecificQuestion(int id);
}
