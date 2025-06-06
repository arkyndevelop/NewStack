package com.examplenewstack.newstack.book.service;

import com.examplenewstack.newstack.book.Book;
import com.examplenewstack.newstack.book.dto.BookResponseDTO;
import com.examplenewstack.newstack.book.exception.NoBooksFoundByIdException;
import com.examplenewstack.newstack.book.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportBookByIDService {

    private final BookRepository bookRepository;

    public ReportBookByIDService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponseDTO findByID(
            int bookID
    ){
        Book bookFound = bookRepository.findById(bookID)
                .orElseThrow(NoBooksFoundByIdException::new);

        return new BookResponseDTO(
                bookFound.getTitle(),
                bookFound.getISBN(),
                bookFound.getCategory(),
                bookFound.getYear_publication(),
                bookFound.isDisponibility(),
                bookFound.getTotal_quantity(),
                bookFound.getDisponibility_quantity(),
                bookFound.getId()
        );
    }
}
