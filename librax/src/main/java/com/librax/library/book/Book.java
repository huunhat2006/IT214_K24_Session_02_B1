package com.librax.library.book;

public class Book {
    private Integer id;
    private String title;

    public Book(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
