package com.examplenewstack.newstack.book.service;

import com.examplenewstack.newstack.book.dto.BookDTO;
import com.examplenewstack.newstack.book.Book;
import com.examplenewstack.newstack.book.exception.NoBooksFoundByISBNException;
import com.examplenewstack.newstack.book.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RegisterBookService {


    private final BookRepository bookRepository;

    public RegisterBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book register(
            @RequestBody BookDTO bookDTO,
            String isbn
    ){
        if(bookRepository.existsByISBN(isbn)){
            throw new NoBooksFoundByISBNException();
        }
        return bookRepository.save(bookDTO.toBook());
    }
}
