package com.edu.victor.domain;

import org.springframework.web.multipart.MultipartFile;

public class Section {
    private int id;
    private int weights;
    private int superior_id;
    private String title;
    private String url;
    private MultipartFile video;
    private String duration;

    public MultipartFile getVideo() {
        return video;
    }

    public void setVideo(MultipartFile video) {
        this.video = video;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getSuperior_id() {
        return superior_id;
    }

    public void setSuperior_id(int superior_id) {
        this.superior_id = superior_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWeights() {
        return weights;
    }

    public void setWeights(int weights) {
        this.weights = weights;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
