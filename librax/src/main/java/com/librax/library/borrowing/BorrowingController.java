package com.librax.library.borrowing;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/borrowings")
public class BorrowingController {
    private final BorrowingService borrowingService;

    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @PostMapping
    public String borrowBook(@RequestBody BorrowingRequest request) {
        borrowingService.borrowBook(request.getMemberId(), request.getBookId());
        return "Book " + request.getBookId() + " borrowed successfully by member " + request.getMemberId();
    }
}
