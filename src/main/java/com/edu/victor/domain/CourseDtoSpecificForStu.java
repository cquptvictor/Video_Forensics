package com.edu.victor.domain;



import java.util.List;

public class CourseDtoSpecificForStu {
    private List<ChapterDtoForStu> chapterDtoList;
    private Integer id;
    public List<ChapterDtoForStu> getChapterDtoList() {
        return chapterDtoList;
    }

    public void setChapterDtoList(List<ChapterDtoForStu> chapterDtoList) {
        this.chapterDtoList = chapterDtoList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
