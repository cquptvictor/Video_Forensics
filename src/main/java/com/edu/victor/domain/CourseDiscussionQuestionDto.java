package com.edu.victor.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**问答区的帖子列表展示*/
public class CourseDiscussionQuestionDto {
    private Integer id;
    private UserDto userDto;
    private String title;
    private String isTeacher;
    private Integer commentNum;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(String isTeacher) {
        this.isTeacher = isTeacher;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
