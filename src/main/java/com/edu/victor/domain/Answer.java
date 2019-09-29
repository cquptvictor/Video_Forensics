package com.edu.victor.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Answer {
    private int id;
    private int qsId;
    private int superiorId;
    private String content;
    private int asrId;
    private String asrName;
    private char isTeacher;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date time;

    public int getQsId() {
        return qsId;
    }

    public void setQsId(int qsId) {
        this.qsId = qsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(int superiorId) {
        this.superiorId = superiorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAsrId() {
        return asrId;
    }

    public void setAsrId(int asrId) {
        this.asrId = asrId;
    }

    public String getAsrName() {
        return asrName;
    }

    public void setAsrName(String asrName) {
        this.asrName = asrName;
    }

    public char getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(char isTeacher) {
        this.isTeacher = isTeacher;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
