package com.examplenewstack.newstack.domain.book.service;

import com.examplenewstack.newstack.domain.book.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteAllBooksService {

    private final BookRepository bookRepository;

    public DeleteAllBooksService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void deleteAll(){
        bookRepository.deleteAll();
    }
}
