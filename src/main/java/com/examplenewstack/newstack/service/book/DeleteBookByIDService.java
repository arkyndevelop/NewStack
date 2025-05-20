package com.examplenewstack.newstack.service.book;

import com.examplenewstack.newstack.entity.book.Book;
import com.examplenewstack.newstack.exceptions.book.NoBooksFoundByIdException;
import com.examplenewstack.newstack.repository.BookRepository;
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
