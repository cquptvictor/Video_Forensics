package com.edu.victor.domain;

public class Chapter {
    private int id;
    private int superior_id;
    private String title;
    private int weights;

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
