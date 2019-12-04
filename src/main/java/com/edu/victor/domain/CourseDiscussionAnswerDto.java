package com.edu.victor.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.ibatis.plugin.Interceptor;

import java.util.Date;

public class CourseDiscussionAnswerDto {
    private Integer id;
    private Integer superiorId;
    private String content;
    private UserDto respondent;
    private String isTeacher;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(Integer superiorId) {
        this.superiorId = superiorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDto getRespondent() {
        return respondent;
    }

    public void setRespondent(UserDto respondent) {
        this.respondent = respondent;
    }

    public String getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(String isTeacher) {
        this.isTeacher = isTeacher;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
