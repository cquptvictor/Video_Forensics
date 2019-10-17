package com.edu.victor.domain;

import javax.validation.constraints.NotNull;

public class Judge {
    @NotNull
    private Integer id;
    @NotNull
    private Double score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
