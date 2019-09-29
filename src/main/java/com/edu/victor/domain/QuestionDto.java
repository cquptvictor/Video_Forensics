package com.edu.victor.domain;
/**问答区的帖子列表展示*/
public class QuestionDto {
    private int id;
    private int qsrId;
    private String qsrName;
    private String title;
    private char isTeacher;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQsrId() {
        return qsrId;
    }

    public void setQsrId(int qsrId) {
        this.qsrId = qsrId;
    }

    public String getQsrName() {
        return qsrName;
    }

    public void setQsrName(String qsrName) {
        this.qsrName = qsrName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public char getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(char isTeacher) {
        this.isTeacher = isTeacher;
    }
}
