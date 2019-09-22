package com.edu.victor.domain;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonInclude;

import java.sql.Date;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Notice {
    private int id;
    private String tea_id;
    private String content;
    private String title;
    private Date publish_time;
    private List<Integer> toList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTea_id() {
        return tea_id;
    }

    public void setTea_id(String tea_id) {
        this.tea_id = tea_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Date publish_time) {
        this.publish_time = publish_time;
    }

    public List<Integer> getToList() {
        return toList;
    }

    public void setToList(List<Integer> toList) {
        this.toList = toList;
    }
}
