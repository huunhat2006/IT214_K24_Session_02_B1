package com.librax.library.borrowing;

public class BorrowingRequest {
    private Integer memberId;
    private Integer bookId;

    public Integer getMemberId() { return memberId; }
    public void setMemberId(Integer memberId) { this.memberId = memberId; }
    public Integer getBookId() { return bookId; }
    public void setBookId(Integer bookId) { this.bookId = bookId; }
}
