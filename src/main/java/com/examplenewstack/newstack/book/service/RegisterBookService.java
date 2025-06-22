package com.examplenewstack.newstack.book.service;

import com.examplenewstack.newstack.book.dto.BookRequestDTO;
import com.examplenewstack.newstack.book.dto.BookResponseDTO;
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

    public BookResponseDTO register(
            BookRequestDTO bookDTO,
            String isbn,
            int collectionID,
            int employeeID
    ){
        Collection collection = collectionRepository.findById(collectionID)
                .orElseThrow();
//        if(bookRepository.existsByISBN(isbn)){
//            throw new NoBooksFoundByISBNException();
//        }

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
                bookDTO.employeeId()
        );
    }
}
