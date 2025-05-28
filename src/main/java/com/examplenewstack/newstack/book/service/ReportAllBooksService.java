package com.examplenewstack.newstack.book.service;

import com.examplenewstack.newstack.book.Book;
import com.examplenewstack.newstack.book.exception.NoBooksFoundException;
import com.examplenewstack.newstack.book.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportAllBooksService {

    private final BookRepository bookRepository;

    public ReportAllBooksService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> reportAllBooks(){
        List<Book> findBooks = bookRepository.findAll();
        if(findBooks.isEmpty()){
            throw new NoBooksFoundException();
        }
        return bookRepository.findAll();
    }
}
