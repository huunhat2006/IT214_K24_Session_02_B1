package com.librax.library.borrowing;

import org.springframework.stereotype.Service;
import com.librax.library.member.MemberService;
import com.librax.library.member.Member;
import com.librax.library.book.BookService;
import com.librax.library.book.Book;

@Service
public class BorrowingService {
    private final MemberService memberService;
    private final BookService bookService;
    private final BorrowingRepository borrowingRepository;

    public BorrowingService(MemberService memberService, BookService bookService, BorrowingRepository borrowingRepository) {
        this.memberService = memberService;
        this.bookService = bookService;
        this.borrowingRepository = borrowingRepository;
    }

    public boolean canBorrowBook(int currentBorrowedByMember) {
        // Sửa lỗi: Nếu số sách đang mượn >= 5 thì không cho mượn nữa (trả về false)
        // Code cũ: currentBorrowedByMember > 5, khiến cho khi đang có 5 sách, 5 > 5 là false nên trả về true (cho mượn).
        return currentBorrowedByMember < 5;
    }

    public void borrowBook(Integer memberId, Integer bookId) {
        Member member = memberService.getMemberById(memberId);
        Book book = bookService.getBookById(bookId);

        if (!canBorrowBook(member.getCurrentlyBorrowedBooks())) {
            throw new RuntimeException("Member has reached the maximum borrowing limit of 5 books.");
        }

        member.setCurrentlyBorrowedBooks(member.getCurrentlyBorrowedBooks() + 1);
        memberService.updateMember(member);
        
        borrowingRepository.saveBorrowingRecord(memberId, bookId);
        
        System.out.println("Member " + memberId + " successfully borrowed book " + bookId);
    }
}
