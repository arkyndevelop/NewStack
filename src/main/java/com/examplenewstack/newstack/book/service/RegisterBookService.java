package com.examplenewstack.newstack.book.service;

import com.examplenewstack.newstack.book.Book;
import com.examplenewstack.newstack.book.dto.BookRequestDTO;
import com.examplenewstack.newstack.book.exception.NoBooksFoundByISBNException;
import com.examplenewstack.newstack.book.repository.BookRepository;
import com.examplenewstack.newstack.collection.Collection;
import com.examplenewstack.newstack.collection.repository.CollectionRepository;
import com.examplenewstack.newstack.employee.Employee;
import com.examplenewstack.newstack.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterBookService {

    private final BookRepository bookRepository;
    private final CollectionRepository collectionRepository;
    private final EmployeeRepository employeeRepository;

    public RegisterBookService(BookRepository bookRepository, CollectionRepository collectionRepository, EmployeeRepository employeeRepository) {
        this.bookRepository = bookRepository;
        this.collectionRepository = collectionRepository;
        this.employeeRepository = employeeRepository;
    }

    public Book register(
            BookRequestDTO bookDTO,
            String isbn,
            Long collectionID,
            Long employeeID
    ){
        Collection collection = collectionRepository.findById(collectionID)
                .orElseThrow();
        if(bookRepository.existsByISBN(isbn)){
            throw new NoBooksFoundByISBNException();
        }

        Employee employeeFound = employeeRepository.findById(employeeID)
                .orElseThrow();
        return bookRepository.save(bookDTO.tobook(collection, employeeFound));
    }
}
