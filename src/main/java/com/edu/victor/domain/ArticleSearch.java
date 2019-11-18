package com.edu.victor.domain;

import javax.validation.constraints.Pattern;

public class ArticleSearch {
    @Pattern(regexp = "(0|1)")
    private Integer isApp;
    private String publisherName;
    private String title;
    @Pattern(regexp = "(book|article|analysis|news)")
    private String type;

    public Integer getIsApp() {
        return isApp;
    }

    public void setIsApp(Integer isApp) {
        this.isApp = isApp;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

