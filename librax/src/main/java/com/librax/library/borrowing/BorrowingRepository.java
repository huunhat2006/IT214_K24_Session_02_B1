package com.librax.library.borrowing;

import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class BorrowingRepository {
    private final List<String> borrowingRecords = new ArrayList<>();

    public void saveBorrowingRecord(Integer memberId, Integer bookId) {
        borrowingRecords.add("Member " + memberId + " borrowed book " + bookId);
    }
    
    public List<String> getAllRecords() {
        return new ArrayList<>(borrowingRecords);
    }
}
