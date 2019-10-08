package com.edu.victor.Dao;

import com.edu.victor.domain.*;

import java.util.Map;

public interface QAndADao {
    Boolean addQuestion(Question question);
    Boolean addReply(Answer answer);
    Page<QuestionDto> getQuestionByPage(Page page);
    QuestionDtoForSpecific getSpecificQuestion(int id);
    User getUser(Map map);
    User getTargetIdByQsId(int id);
    User getTargetIdByRpId(int id);
}
