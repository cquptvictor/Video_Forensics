package com.edu.victor.domain;


import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Teacher extends User{


    private String email;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
