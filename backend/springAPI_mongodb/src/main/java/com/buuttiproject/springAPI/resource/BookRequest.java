package com.buuttiproject.springAPI.resource;

public class BookRequest {

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    private String author;
    private String title;
    private String description;

    public BookRequest(String author, String title, String description) {
        this.author = author;
        this.title = title;
        this.description = description;
    }



}
