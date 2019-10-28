package com.edu.victor.domain;



public class CourseForApp {
    private Integer id;
    private String title;
    private String description;
    private String teaName ;
    private Integer alreadyIn;
    private String url;
    private String graduated;

    public String getGraduated() {
        return graduated;
    }

    public void setGraduated(String graduated) {
        this.graduated = graduated;
    }

    public Integer getAlreadyIn() {
        return alreadyIn;
    }

    public void setAlreadyIn(Integer alreadyIn) {
        this.alreadyIn = alreadyIn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
