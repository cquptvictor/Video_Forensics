package com.edu.victor.domain;

import javax.validation.constraints.NotNull;

public class SbHomework {
    private int id;
    @NotNull
    private Integer hwId;
    private double score;
    private String status;
    private String name;
    private String username;

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


}
