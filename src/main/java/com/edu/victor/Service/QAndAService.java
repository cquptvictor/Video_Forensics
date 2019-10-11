package com.edu.victor.Service;

import com.edu.victor.domain.*;

public interface QAndAService  {
    ResponseData addQustion(CourseDiscussionQuestion question, User user);
    ResponseData relpy(CourseDiscussionAnswer answer, User user);
    ResponseData getQuestionList(Page page);
    ResponseData getSpecificQuestion(int id);
}
