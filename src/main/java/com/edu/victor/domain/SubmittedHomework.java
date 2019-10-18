package com.edu.victor.domain;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

public class SubmittedHomework {
    @NotNull
    private MultipartFile file;
    @NotNull
    private Integer hwId;
    private String url;
    private Integer stuId;

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Integer getHwId() {
        return hwId;
    }

    public void setHwId(Integer hwId) {
        this.hwId = hwId;
    }
}
