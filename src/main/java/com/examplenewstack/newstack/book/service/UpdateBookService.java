package com.examplenewstack.newstack.book.service;

import com.examplenewstack.newstack.book.dto.BookDTO;
import com.examplenewstack.newstack.book.Book;
import com.examplenewstack.newstack.book.exception.NoBooksFoundByISBNException;
import com.examplenewstack.newstack.book.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateBookService {

    private final BookRepository bookRepository;

    public UpdateBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public ResponseEntity<Book> updateBook(
            BookDTO bookDTO,
            String bookISBN
    ){
        Book bookExists = bookRepository.findByISBN(bookISBN)
                .orElseThrow(NoBooksFoundByISBNException::new);

        bookExists.setTitle(bookDTO.getTitle());
        bookExists.setISBN(bookDTO.getISBN());
        bookExists.setCategory(bookDTO.getCategory());
        bookExists.setYear_publication(bookDTO.getYear_publication());
        bookExists.setDisponibility(bookDTO.isDisponibility()); // Verificar sobre a disponilidade!
        bookExists.setDisponibility_quantity(bookDTO.getDisponibility_quantity());
        bookExists.setISBN(bookDTO.getISBN());

        Book updateBook = bookRepository.save(bookExists);
        return ResponseEntity
                .ok(updateBook);
    }
}
