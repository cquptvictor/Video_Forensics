package com.edu.victor.domain;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnore;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Chapter {
    private int id;
    @JsonIgnore
    private int superior_id;
    private String title;
    @JsonIgnore
    private int weights;
    @JsonIgnore
    private int tea_id;

    public int getTea_id() {
        return tea_id;
    }

    public void setTea_id(int tea_id) {
        this.tea_id = tea_id;
    }

    public int getSuperior_id() {
        return superior_id;
    }

    public void setSuperior_id(int superior_id) {
        this.superior_id = superior_id;
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
