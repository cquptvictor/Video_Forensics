package com.edu.victor.domain;

import javax.validation.constraints.NotNull;

public class SubmittedHomeworkDto {
    private int id;
    @NotNull
    private Integer hwId;
    private Double score = null;
    private String name;
    private String username;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // private Student student;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getHwId() {
        return hwId;
    }

    public void setHwId(Integer hwId) {
        this.hwId = hwId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

}
