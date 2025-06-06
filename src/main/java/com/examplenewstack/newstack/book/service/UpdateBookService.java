package com.examplenewstack.newstack.book.service;

import com.examplenewstack.newstack.book.Book;
import com.examplenewstack.newstack.book.dto.BookRequestDTO;
import com.examplenewstack.newstack.book.dto.BookResponseDTO;
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

    public ResponseEntity<BookResponseDTO> updateBook(
            BookRequestDTO request,
            String bookISBN
    ){
        Book bookExists = bookRepository.findByISBN(bookISBN)
                .orElseThrow(NoBooksFoundByISBNException::new);

        bookExists.setTitle(request.title());
        bookExists.setISBN(request.ISBN());
        bookExists.setCategory(request.category());
        bookExists.setYear_publication(request.year_publication());
        bookExists.setDisponibility(request.disponibility()); // Verificar sobre a disponilidade!
        bookExists.setDisponibility_quantity(request.disponibility_quantity());

        Book updateBook = bookRepository.save(bookExists);
        return ResponseEntity
                .ok(new BookResponseDTO(
                        bookExists.getTitle(),
                        bookExists.getISBN(),
                        bookExists.getCategory(),
                        bookExists.getYear_publication(),
                        bookExists.isDisponibility(),
                        bookExists.getTotal_quantity(),
                        bookExists.getDisponibility_quantity(),
                        bookExists.getId()
                ));
    }
}
