package com.examplenewstack.newstack.service.book;

import com.examplenewstack.newstack.dtos.book.BookDTO;
import com.examplenewstack.newstack.entity.librarie.book.Book;
import com.examplenewstack.newstack.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RegisterBookService {

    @Autowired
    private final BookRepository bookRepository;

    public RegisterBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public Book register(
            @RequestBody BookDTO bookDTO
    ){
        if(bookRepository.existsByTitle(bookDTO.getTitle())){
            //throw new CustomException("Já existe um livro com esse título!");
        }
        Book book = bookDTO.toBook();

        return bookRepository.save(book);
    }
}
