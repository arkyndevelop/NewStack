package com.examplenewstack.newstack.domain.book.service;

import com.examplenewstack.newstack.domain.book.dto.BookRequestDTO;
import com.examplenewstack.newstack.domain.book.dto.BookResponseDTO;
import com.examplenewstack.newstack.domain.book.repository.BookRepository;
import com.examplenewstack.newstack.domain.collection.Collection;
import com.examplenewstack.newstack.domain.collection.repository.CollectionRepository;
import com.examplenewstack.newstack.domain.employee.Employee;
import com.examplenewstack.newstack.domain.employee.repository.EmployeeRepository;
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
                bookDTO.employeeId(),
                bookDTO.author(),
                bookDTO.description(),
                bookDTO.publisher(),
                bookDTO.thumbnailUrl()
        );
    }
}
