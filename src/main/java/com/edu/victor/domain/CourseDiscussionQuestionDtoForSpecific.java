package com.edu.victor.domain;

import java.util.List;

public class CourseDiscussionQuestionDtoForSpecific extends CourseDiscussionQuestion {
    private List<CourseDiscussionAnswerDto> answerList;

    public List<CourseDiscussionAnswerDto> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<CourseDiscussionAnswerDto> answerList) {
        this.answerList = answerList;
    }
}
