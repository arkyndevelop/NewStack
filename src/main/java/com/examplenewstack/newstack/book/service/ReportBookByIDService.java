package com.examplenewstack.newstack.book.service;

import com.examplenewstack.newstack.book.Book;
import com.examplenewstack.newstack.book.exception.NoBooksFoundByIdException;
import com.examplenewstack.newstack.book.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportBookByIDService {

    private final BookRepository bookRepository;

    public ReportBookByIDService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findByID(
            Long bookID
    ){
        return bookRepository.findById(bookID)
                .orElseThrow(NoBooksFoundByIdException::new);
    }
}
