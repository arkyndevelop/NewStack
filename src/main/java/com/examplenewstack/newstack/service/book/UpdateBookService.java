package com.examplenewstack.newstack.service.book;

import com.examplenewstack.newstack.dtos.book.BookDTO;
import com.examplenewstack.newstack.entity.book.Book;
import com.examplenewstack.newstack.exceptions.book.NoBooksFoundByISBNException;
import com.examplenewstack.newstack.repository.BookRepository;
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
        bookExists.setDisponibility(bookDTO.toBook().isDisponibility()); // Verificar sobre a disponilidade!
        bookExists.setDisponibility_quantity(bookDTO.getDisponibility_quantity());
        bookExists.setISBN(bookDTO.getISBN());

        Book updateBook = bookRepository.save(bookExists);
        return ResponseEntity
                .ok(updateBook);
    }
}
