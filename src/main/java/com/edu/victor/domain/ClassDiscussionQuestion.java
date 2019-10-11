package com.edu.victor.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**接受用户的提问的参数
 * 展示问题详情页面*/
public class ClassDiscussionQuestion {
    private int id;
    private int ClassId;
    private String title;
    private String content;
    private char isTeacher;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date time;
    @JsonIgnore
    private int qsrId;
    private UserDto userDto;

    public int getQsrId() {
        return qsrId;
    }

    public void setQsrId(int qsrId) {
        this.qsrId = qsrId;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

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

    public int getClassId() {
        return ClassId;
    }

    public void setClassId(int classId) {
        ClassId = classId;
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
