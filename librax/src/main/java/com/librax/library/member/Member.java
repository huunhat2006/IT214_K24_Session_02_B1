package com.librax.library.member;

public class Member {
    private Integer id;
    private String name;
    private int currentlyBorrowedBooks;

    public Member(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.currentlyBorrowedBooks = 0;
    }
    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getCurrentlyBorrowedBooks() { return currentlyBorrowedBooks; }
    public void setCurrentlyBorrowedBooks(int currentlyBorrowedBooks) { this.currentlyBorrowedBooks = currentlyBorrowedBooks; }
}
