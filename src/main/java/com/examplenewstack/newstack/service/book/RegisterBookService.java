package com.examplenewstack.newstack.service.book;

import com.examplenewstack.newstack.dtos.book.BookDTO;
import com.examplenewstack.newstack.entity.book.Book;
import com.examplenewstack.newstack.exceptions.book.NoBooksFoundByISBNException;
import com.examplenewstack.newstack.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
