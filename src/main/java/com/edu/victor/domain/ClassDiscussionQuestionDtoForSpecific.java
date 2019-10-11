package com.edu.victor.domain;

import java.util.List;

public class ClassDiscussionQuestionDtoForSpecific extends CourseDiscussionQuestion {
    private List<ClassDiscussionAnswerDto> answerList;

    public List<ClassDiscussionAnswerDto> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<ClassDiscussionAnswerDto> answerList) {
        this.answerList = answerList;
    }
}
