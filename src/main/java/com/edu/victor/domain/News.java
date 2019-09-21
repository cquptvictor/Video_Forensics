package com.edu.victor.domain;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class News {
    private int id;
    private String title;
    private String content;
    private int publisherId;
    private String publisherName;
    private Date publishTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
            this.publisherName = publisherName;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }
}
