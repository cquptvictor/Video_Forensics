package com.edu.victor.domain;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**传小说*/
public class Book {
    @NotNull
    private MultipartFile book;
    @NotNull
    @Size(min=1,max = 15)
    private String title;

    private String description;
    @NotNull
    private MultipartFile image;

    public MultipartFile getBook() {
        return book;
    }

    public void setBook(MultipartFile book) {
        this.book = book;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
