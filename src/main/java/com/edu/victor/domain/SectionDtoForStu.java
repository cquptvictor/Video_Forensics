package com.edu.victor.domain;

public class SectionDtoForStu extends Section{
    private String over;
    private Double location;

    public Double getLocation() {
        return location;
    }

    public void setLocation(Double location) {
        this.location = location;
    }

    public String getOver() {
        return over;
    }

    public void setOver(String over) {
        this.over = over;
    }

}
