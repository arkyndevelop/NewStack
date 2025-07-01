package com.examplenewstack.newstack.domain.book.service;

import com.examplenewstack.newstack.domain.book.Book;
import com.examplenewstack.newstack.domain.book.dto.BookRequestDTO;
import com.examplenewstack.newstack.domain.book.dto.BookResponseDTO;
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
    public BookResponseDTO register(
            BookRequestDTO bookDTO,
            String isbn,
            int collectionID,
            int employeeID
    ){
        Collection collection = collectionRepository.findById(collectionID)
                .orElseThrow();

        Employee employeeFound = employeeRepository.findById(employeeID)
                .orElseThrow();

        bookRepository.save(bookDTO.tobook(collection, employeeFound));

        return new BookResponseDTO(
                bookDTO.title(),
                bookDTO.ISBN(),
                bookDTO.category(),
                bookDTO.year_publication(),
                bookDTO.disponibility(),
                bookDTO.total_quantity(),
                bookDTO.disponibility_quantity(),
                bookDTO.collectionId(),
                bookDTO.employeeId(),
                bookDTO.author(),
                bookDTO.description(),
                bookDTO.publisher(),
                bookDTO.thumbnailUrl()
        );
    }

    //Função responsavel por mostrar todos os livros
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
                        book.getEmployee().getId(),
                        book.getAuthor(),
                        book.getDescription(),
                        book.getPublisher(),
                        book.getThumbnailUrl()
                ))
                .toList();
    }
    //Função responsavel por mostrar os livros pelo ID
    public BookResponseDTO findByID(int bookID) {
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
                bookFound.getCollection().getId(),
                bookFound.getEmployee().getId(),
                bookFound.getAuthor(),
                bookFound.getDescription(),
                bookFound.getPublisher(),
                bookFound.getThumbnailUrl()
        );
    }

    //Função responsavel por atualizar um livro pelo ID
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
                        bookExists.getCollection().getId(),
                        bookExists.getEmployee().getId(),
                        bookExists.getAuthor(),
                        bookExists.getDescription(),
                        bookExists.getPublisher(),
                        bookExists.getThumbnailUrl()
                ));
    }


    //Função responsavel por deletar todos os livros
    public void deleteAll(){

        if(bookRepository.count() == 0){
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





}
