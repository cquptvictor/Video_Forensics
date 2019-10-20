package com.edu.victor.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class Answer {
    private Integer id;
    @NotNull
    private Integer superiorId;
    @NotNull
    private Integer qsId;

    private Integer asrId;

    @NotEmpty
    private String content;
    private String isTeacher;
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
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

    public Integer getQsId() {
        return qsId;
    }

    public void setQsId(Integer qsId) {
        this.qsId = qsId;
    }

    public Integer getAsrId() {
        return asrId;
    }

    public void setAsrId(Integer asrId) {
        this.asrId = asrId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
