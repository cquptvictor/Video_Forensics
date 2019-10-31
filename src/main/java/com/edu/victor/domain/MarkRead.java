package com.edu.victor.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class MarkRead {
    @NotNull
    private Integer msgUserId;
    @Pattern(regexp = "(0|1)")
    private String type;

    public Integer getMsgUserId() {
        return msgUserId;
    }

    public void setMsgUserId(Integer msgUserId) {
        this.msgUserId = msgUserId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
