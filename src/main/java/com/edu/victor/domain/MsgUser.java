package com.edu.victor.domain;
/**消息发送给哪些用户*/
public class MsgUser {
    private int id;
    private int targetId;
    private String isTeacher;
    private int msgId;
    private int read;

    public MsgUser(int targetId, String isTeacher, int msgId) {
        this.targetId = targetId;
        this.isTeacher = isTeacher;
        this.msgId = msgId;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public String getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(String isTeacher) {
        this.isTeacher = isTeacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }
}
