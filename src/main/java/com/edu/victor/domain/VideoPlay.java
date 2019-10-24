package com.edu.victor.domain;

import javax.validation.constraints.NotNull;

public class VideoPlay {
    @NotNull
    private Integer secId;//视频所在小节的id
    private String location;//视频播放到的进度
    private String over;//视频是否播放完毕
    private Integer stuId;

    public Integer getSecId() {
        return secId;
    }

    public void setSecId(Integer secId) {
        this.secId = secId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }
}