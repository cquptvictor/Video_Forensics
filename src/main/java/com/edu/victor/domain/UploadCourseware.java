package com.edu.victor.domain;

import org.springframework.web.multipart.MultipartFile;

public class UploadCourseware {
    private int id;
    private MultipartFile[] multipartFile;
    private Integer superiorId;

    public Integer getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(Integer superiorId) {
        this.superiorId = superiorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MultipartFile[] getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile[] multipartFile) {
        this.multipartFile = multipartFile;
    }
}
