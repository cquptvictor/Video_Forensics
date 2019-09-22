package com.edu.victor.domain;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonFormat;
import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Courseware {
    private int id;
    private int superior_id;
    private MultipartFile file;
    private String url;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date time;
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSuperior_id() {
        return superior_id;
    }

    public void setSuperior_id(int superior_id) {
        this.superior_id = superior_id;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public Date getTime() {

        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }
}
