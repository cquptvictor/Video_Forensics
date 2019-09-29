package com.edu.victor.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Question {
    private int id;
    private int qsrId;
    private int courseId;
    private String qsrName;
    private String title;
    private String content;
    private char isTeacher;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date time;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public char getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(char isTeacher) {
        this.isTeacher = isTeacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQsrId() {
        return qsrId;
    }

    public void setQsrId(int qsrId) {
        this.qsrId = qsrId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getQsrName() {
        return qsrName;
    }

    public void setQsrName(String qsrName) {
        this.qsrName = qsrName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
