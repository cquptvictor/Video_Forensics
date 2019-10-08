package com.edu.victor.domain;

import java.util.List;

public class QuestionDtoForSpecific extends Question{
    private List<AnswerDto> answerList;

    public List<AnswerDto> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerDto> answerList) {
        this.answerList = answerList;
    }
}
