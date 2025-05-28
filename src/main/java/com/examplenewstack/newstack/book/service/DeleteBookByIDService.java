package com.examplenewstack.newstack.book.service;

import com.examplenewstack.newstack.book.Book;
import com.examplenewstack.newstack.book.exception.NoBooksFoundByIdException;
import com.examplenewstack.newstack.book.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteBookByIDService {

    private final BookRepository bookRepository;

    public DeleteBookByIDService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public ResponseEntity<?> deleteByID(
            Long bookID
    ) {
        Book findBook = bookRepository.findById(bookID)
                .orElseThrow(NoBooksFoundByIdException::new);

        bookRepository.delete(findBook);
        return ResponseEntity
                .ok()
                .build();
    }
}
