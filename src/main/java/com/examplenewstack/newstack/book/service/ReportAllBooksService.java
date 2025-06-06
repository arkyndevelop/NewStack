package com.examplenewstack.newstack.book.service;

import com.examplenewstack.newstack.book.Book;
import com.examplenewstack.newstack.book.dto.BookResponseDTO;
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

    public List<BookResponseDTO> reportAllBooks(){
        List<Book> findBooks = bookRepository.findAll();
        if(findBooks.isEmpty()){
            throw new NoBooksFoundException();
        }
        return findBooks
                .stream()
                .map(books -> new BookResponseDTO(
                        books.getTitle(),
                        books.getISBN(),
                        books.getCategory(),
                        books.getYear_publication(),
                        books.isDisponibility(),
                        books.getTotal_quantity(),
                        books.getDisponibility_quantity(),
                        books.getCollection().getId()))
                .toList();
    }
}
