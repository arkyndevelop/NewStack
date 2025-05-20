package com.examplenewstack.newstack.service.book;

import com.examplenewstack.newstack.entity.book.Book;
import com.examplenewstack.newstack.exceptions.book.NoBooksFoundException;
import com.examplenewstack.newstack.repository.BookRepository;
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
