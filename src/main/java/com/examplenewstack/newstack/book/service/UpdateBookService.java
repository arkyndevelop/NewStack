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

    public ResponseEntity<BookResponseDTO> updateBook(BookRequestDTO request, String bookISBN) {
        Book bookExists = bookRepository.findByISBN(bookISBN)
                .orElseThrow(NoBooksFoundByISBNException::new);

        // Atualização dos campos existentes com dados do request
        bookExists.setTitle(request.title());
        bookExists.setISBN(request.ISBN());
        bookExists.setCategory(request.category());
        bookExists.setYear_publication(request.year_publication());
        bookExists.setDisponibility(request.disponibility());
        bookExists.setTotal_quantity(request.total_quantity()); // Não esquecer de atualizar a quantidade total
        bookExists.setDisponibility_quantity(request.disponibility_quantity());

        // Atualização dos novos atributos adicionados na entidade
        bookExists.setAuthor(request.author());
        bookExists.setDescription(request.description());
        bookExists.setPublisher(request.publisher());
        bookExists.setThumbnailUrl(request.thumbnailUrl());

        // Salva as alterações no banco e obtém o objeto atualizado
        Book updatedBook = bookRepository.save(bookExists);

        // Retorna o DTO com todos os campos atualizados, usando o objeto salvo no banco
        return ResponseEntity.ok(new BookResponseDTO(
                updatedBook.getTitle(),
                updatedBook.getISBN(),
                updatedBook.getCategory(),
                updatedBook.getYear_publication(),
                updatedBook.isDisponibility(),
                updatedBook.getTotal_quantity(),
                updatedBook.getDisponibility_quantity(),
                updatedBook.getCollection().getId(),
                updatedBook.getAuthor(),
                updatedBook.getDescription(),
                updatedBook.getPublisher(),
                updatedBook.getThumbnailUrl()
        ));
    }
}
