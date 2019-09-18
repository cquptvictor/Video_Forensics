package com.edu.victor.domain;

public class SubmitHomework {
    private int id;
    private int hw_id;
    private String content;
    private double score;
    private String status;
    private String stu_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHw_id() {
        return hw_id;
    }

    public void setHw_id(int hw_id) {
        this.hw_id = hw_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }
}
