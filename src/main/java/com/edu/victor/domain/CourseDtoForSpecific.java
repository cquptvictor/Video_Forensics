package com.edu.victor.domain;


import java.util.List;
public class CourseDtoForSpecific {
    private List<ChapterDto> chapterDtoList;
    private Integer id;

    public List<ChapterDto> getChapterDtoList() {
        return chapterDtoList;
    }

    public void setChapterDtoList(List<ChapterDto> chapterDtoList) {
        this.chapterDtoList = chapterDtoList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
