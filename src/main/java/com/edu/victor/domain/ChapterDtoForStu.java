package com.edu.victor.domain;

import java.util.List;

public class ChapterDtoForStu extends Chapter {
    private List<SectionDtoForStu> sectionList;

    public List<SectionDtoForStu> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<SectionDtoForStu> sectionList) {
        this.sectionList = sectionList;
    }
}
