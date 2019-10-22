package com.edu.victor.domain;

/**接收课程加入时的参数*/
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
public class CourseJoin {
    @NotNull
    private Integer id;
    @NotEmpty
    @Size(min=6,max = 6)
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
