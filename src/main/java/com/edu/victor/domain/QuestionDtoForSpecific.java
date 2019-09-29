package com.edu.victor.domain;

import java.util.List;

public class QuestionDtoForSpecific extends Question{
    List<Answer> answerList;

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }
}
