package com.edu.victor.domain;


import javax.validation.constraints.Pattern;

/**接收app课程页面传来的参数*/
public class CourseSearchForApp {
    @Pattern(regexp = "(all|my)")
    private String type;
    private String title;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
