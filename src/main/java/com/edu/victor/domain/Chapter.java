package com.edu.victor.domain;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnore;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Chapter {
    private int id;
    @JsonIgnore
    private int superiorId;
    private String title;
    @JsonIgnore
    private int weights;
    @JsonIgnore
    private int teaId;

    public int getTeaId() {
        return teaId;
    }

    public void setTeaId(int teaId) {
        this.teaId = teaId;
    }

    public int getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(int superiorId) {
        this.superiorId = superiorId;
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
}
