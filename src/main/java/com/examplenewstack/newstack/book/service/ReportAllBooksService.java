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

    public List<BookResponseDTO> reportAllBooks() {
        List<Book> findBooks = bookRepository.findAll();
        if (findBooks.isEmpty()) {
            throw new NoBooksFoundException();
        }
        return findBooks
                .stream()
                .map(book -> new BookResponseDTO(
                        book.getTitle(),
                        book.getISBN(),
                        book.getCategory(),
                        book.getYear_publication(),
                        book.isDisponibility(),
                        book.getTotal_quantity(),
                        book.getDisponibility_quantity(),
                        book.getCollection().getId(),

                        // Novos campos adicionados ao DTO para retornar mais informações do livro
                        book.getAuthor(),
                        book.getDescription(),
                        book.getPublisher(),
                        book.getThumbnailUrl()
                ))
                .toList();
    }
}
