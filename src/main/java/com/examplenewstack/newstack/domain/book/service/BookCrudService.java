package com.examplenewstack.newstack.domain.book.service;

import com.examplenewstack.newstack.domain.book.Book;
import com.examplenewstack.newstack.domain.book.dto.BookRequest;
import com.examplenewstack.newstack.domain.book.dto.BookResponse;
import com.examplenewstack.newstack.domain.book.exception.NoBooksFoundByISBNException;
import com.examplenewstack.newstack.domain.book.exception.NoBooksFoundByIdException;
import com.examplenewstack.newstack.domain.book.exception.NoBooksFoundException;
import com.examplenewstack.newstack.domain.book.repository.BookRepository;
import com.examplenewstack.newstack.domain.collection.Collection;
import com.examplenewstack.newstack.domain.collection.repository.CollectionRepository;
import com.examplenewstack.newstack.domain.employee.Employee;
import com.examplenewstack.newstack.domain.employee.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCrudService {

    private final BookRepository bookRepository;
    private final CollectionRepository collectionRepository;
    private final EmployeeRepository employeeRepository;

    public BookCrudService(BookRepository bookRepository, CollectionRepository collectionRepository, EmployeeRepository employeeRepository, EmployeeRepository employeeRepositor) {
        this.bookRepository = bookRepository;
        this.collectionRepository = collectionRepository;
        this.employeeRepository = employeeRepository;
    }


    //Função responsavel por registrar um livro
    public BookResponse register(
            BookRequest bookDTO
    ) {
//        Collection collection = collectionRepository.findById(collectionID)
//                .orElseThrow();
//
//        Employee employeeFound = employeeRepository.findById(employeeID)
//                .orElseThrow();

        bookRepository.save(bookDTO.tobook());

        return new BookResponse(
                0, // Modificar e corrigir
                bookDTO.title(),
                bookDTO.ISBN(),
                bookDTO.category(),
                bookDTO.year_publication(),
                bookDTO.total_quantity(),
                bookDTO.author(),
                bookDTO.description(),
                bookDTO.publisher(),
                bookDTO.thumbnailUrl()
        );
    }

    //Função responsavel por mostrar todos os livros
    public List<BookResponse> reportAllBooks() {
        List<Book> findBooks = bookRepository.findAll();
        if (findBooks.isEmpty()) {
            throw new NoBooksFoundException();
        }
        return findBooks
                .stream()
                .map(book -> new BookResponse(
                        book.getId(),
                        book.getTitle(),
                        book.getISBN(),
                        book.getCategory(),
                        book.getYear_publication(),
                        book.getTotal_quantity(),
                        book.getAuthor(),
                        book.getDescription(),
                        book.getPublisher(),
                        book.getThumbnailUrl()
                ))
                .toList();
    }

    //Função responsavel por mostrar os livros pelo ID
    public BookResponse findByID(int bookID) {
        Book bookFound = bookRepository.findById(bookID)
                .orElseThrow(NoBooksFoundByIdException::new);

        return new BookResponse(
                bookFound.getId(),
                bookFound.getTitle(),
                bookFound.getISBN(),
                bookFound.getCategory(),
                bookFound.getYear_publication(),
                bookFound.getTotal_quantity(),
                bookFound.getAuthor(),
                bookFound.getDescription(),
                bookFound.getPublisher(),
                bookFound.getThumbnailUrl()
        );
    }

    //Função responsavel por atualizar um livro pelo ID
    public ResponseEntity<BookResponse> updateBook(BookRequest request, String bookISBN) {
        Book bookExists = bookRepository.findByISBN(bookISBN)
                .orElseThrow(NoBooksFoundByISBNException::new);

        // Atualização dos campos existentes com dados do request
        bookExists.setTitle(request.title());
        bookExists.setISBN(request.ISBN());
        bookExists.setCategory(request.category());
        bookExists.setYear_publication(request.year_publication());
        bookExists.setTotal_quantity(request.total_quantity()); // Não esquecer de atualizar a quantidade total

        Book updateBook = bookRepository.save(bookExists);
        return ResponseEntity
                .ok(new BookResponse(
                        bookExists.getId(),
                        bookExists.getTitle(),
                        bookExists.getISBN(),
                        bookExists.getCategory(),
                        bookExists.getYear_publication(),
                        bookExists.getTotal_quantity(),
                        bookExists.getAuthor(),
                        bookExists.getDescription(),
                        bookExists.getPublisher(),
                        bookExists.getThumbnailUrl()
                ));
    }


    //Função responsavel por deletar todos os livros
    public void deleteAll() {

        if (bookRepository.count() == 0) {
            throw new NoBooksFoundException();
        }
        bookRepository.deleteAll();
    }

    //Função responsavel por deletar livro pelo ID
    public ResponseEntity<?> deleteByID(
            Integer bookID
    ) {
        Book findBook = bookRepository.findById(bookID)
                .orElseThrow(NoBooksFoundByIdException::new);

        bookRepository.delete(findBook);
        return ResponseEntity
                .ok()
                .build();
    }

    //Função responsável por fazer o empréstimo do livro
    public Boolean borrowBook(int id, int quant) {
        Optional<Book> bookID = this.bookRepository.findById(id);
        if (bookID.isPresent()) {
            Book book = bookID.get();
            if (book.getDisponibility_quantity() >= quant && quant > 0) {
                book.setDisponibility_quantity(book.getDisponibility_quantity() - quant);
                if (book.getDisponibility_quantity() == 0) {
                    book.setDisponibility(false);
                }
                this.bookRepository.save(book);
                return true;
            } else {
                throw new NoBooksFoundException("Quantidade insuficiente disponível para empréstimo");
            }
        }
        throw new NoBooksFoundException();
    }
}
