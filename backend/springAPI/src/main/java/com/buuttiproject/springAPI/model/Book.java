package com.buuttiproject.springAPI.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.concurrent.atomic.AtomicInteger;

@Document("books")
public class Book {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private int id;
    private String author;
    private String title;
    private String description;

    public Book(String author, String title, String description){
        this.id = ID_GENERATOR.getAndIncrement();
        this.author = author;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
}
