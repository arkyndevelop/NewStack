package com.examplenewstack.newstack.book.service;

import com.examplenewstack.newstack.book.Book;
import com.examplenewstack.newstack.book.dto.BookRequestDTO;
import com.examplenewstack.newstack.book.exception.NoBooksFoundByISBNException;
import com.examplenewstack.newstack.book.repository.BookRepository;
import com.examplenewstack.newstack.collection.Collection;
import com.examplenewstack.newstack.collection.repository.CollectionRepository;
import com.examplenewstack.newstack.employee.Employee;
import com.examplenewstack.newstack.employee.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class RegisterBookService {

    private final BookRepository bookRepository;
    private final CollectionRepository collectionRepository;
    private final EmployeeRepository employeeRepository;

    public RegisterBookService(BookRepository bookRepository,
                               CollectionRepository collectionRepository,
                               EmployeeRepository employeeRepository) {
        this.bookRepository = bookRepository;
        this.collectionRepository = collectionRepository;
        this.employeeRepository = employeeRepository;
    }

    public Book register(BookRequestDTO bookDTO, MultipartFile imagem) {
        String isbn = bookDTO.ISBN();
        int collectionID = bookDTO.collectionId();
        Long employeeID = bookDTO.employeeId();

        Collection collection = collectionRepository.findById(collectionID)
                .orElseThrow(() -> new RuntimeException("Coleção não encontrada"));

        if (bookRepository.existsByISBN(isbn)) {
            throw new NoBooksFoundByISBNException();
        }

        Employee employeeFound = employeeRepository.findById(employeeID)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        Book book = bookDTO.tobook(collection, employeeFound);

        // Se quiser, pode implementar lógica para salvar a imagem aqui

        return bookRepository.save(book);
    }

    public List<Book> listAll() {
        return bookRepository.findAll();
    }
}