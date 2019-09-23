package com.edu.victor.domain;


import java.util.List;
public class CourseDto{
    private List<ChapterDto> chapterDtoList;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ChapterDto> getChapterDtoList() {
        return chapterDtoList;
    }

    public void setChapterDtoList(List<ChapterDto> chapterDtoList) {
        this.chapterDtoList = chapterDtoList;
    }
}
