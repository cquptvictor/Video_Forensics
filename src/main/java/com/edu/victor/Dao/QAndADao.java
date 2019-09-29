package com.edu.victor.Dao;

import com.edu.victor.domain.*;

public interface QAndADao {
    Boolean addQuestion(Question question);
    Boolean addReply(Answer answer);
    Page<QuestionDto> getQuestionByPage(Page page);
    QuestionDtoForSpecific getSpecificQuestion(int id);
}
