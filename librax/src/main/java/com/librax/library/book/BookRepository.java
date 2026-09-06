package com.librax.library.book;

import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class BookRepository {
    private final Map<Integer, Book> books = new HashMap<>();

    public BookRepository() {
        books.put(1, new Book(1, "Clean Code"));
        books.put(2, new Book(2, "Design Patterns"));
    }

    public Optional<Book> findById(Integer id) {
        return Optional.ofNullable(books.get(id));
    }
    
    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }
}
