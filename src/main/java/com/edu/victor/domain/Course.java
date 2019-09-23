package com.edu.victor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;
public class Course {
    private int id;
    private String title;
    private String description;
    private int tea_id;
    private String tea_name;
    private String picUrl;
    @JsonIgnore
    private MultipartFile pic;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public MultipartFile getPic() {
        return pic;
    }

    public void setPic(MultipartFile pic) {
        this.pic = pic;
    }

    public String getTea_name() {
        return tea_name;
    }

    public void setTea_name(String tea_name) {
        this.tea_name = tea_name;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTea_id() {
        return tea_id;
    }

    public void setTea_id(int tea_id) {
        this.tea_id = tea_id;
    }
}
