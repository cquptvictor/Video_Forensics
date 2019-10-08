package com.edu.victor.Service;

import com.edu.victor.domain.*;

public interface QAndAService  {
    ResponseData addQustion(Question question, User user);
    ResponseData relpy(Answer answer, User user);
    ResponseData getQuestionList(Page page);
    ResponseData getSpecificQuestion(int id);
}
