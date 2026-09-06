package com.librax.library.book;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }
    
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
