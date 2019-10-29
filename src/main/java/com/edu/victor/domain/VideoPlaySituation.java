package com.edu.victor.domain;

public class VideoPlaySituation {
    private Double total;
    private Double alreadyOver;
    private Integer stuId;
    private Double progress;
    private Integer courseId;

    @Override
    public String toString() {
        return "VideoPlaySituation{" +
                "total=" + total +
                ", alreadyOver=" + alreadyOver +
                ", stuId=" + stuId +
                ", progress=" + progress +
                ", courseId=" + courseId +
                '}';
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getAlreadyOver() {
        return alreadyOver;
    }

    public void setAlreadyOver(Double alreadyOver) {
        this.alreadyOver = alreadyOver;
    }
}
