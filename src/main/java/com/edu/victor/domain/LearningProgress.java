package com.edu.victor.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

/**学生学习进度*/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LearningProgress {
    private Integer id;
    private Integer courseId;
    private double learningProgress;
    private char graduated;
    private Student student;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public double getLearningProgress() {
        return learningProgress;
    }

    public void setLearningProgress(double learningProgress) {
        this.learningProgress = learningProgress;
    }

    public char getGraduated() {
        return graduated;
    }

    public void setGraduated(char graduated) {
        this.graduated = graduated;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
