package com.edu.victor.domain;


import java.util.List;
public class CourseDto{
   private List<ChapterDto> chapterDtoList;

    public List<ChapterDto> getChapterDtoList() {
        return chapterDtoList;
    }

    public void setChapterDtoList(List<ChapterDto> chapterDtoList) {
        this.chapterDtoList = chapterDtoList;
    }
}
