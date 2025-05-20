package com.examplenewstack.newstack.service.book;

import com.examplenewstack.newstack.entity.book.Book;
import com.examplenewstack.newstack.exceptions.book.NoBooksFoundByIdException;
import com.examplenewstack.newstack.repository.BookRepository;
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
