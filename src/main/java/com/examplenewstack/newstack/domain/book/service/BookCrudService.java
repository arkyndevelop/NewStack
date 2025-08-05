package com.examplenewstack.newstack.domain.book.service;

import com.examplenewstack.newstack.domain.book.Book;
import com.examplenewstack.newstack.domain.book.dto.BookRequest;
import com.examplenewstack.newstack.domain.book.dto.BookResponse;
import com.examplenewstack.newstack.domain.book.dto.BookResponseViewDetails;
import com.examplenewstack.newstack.domain.book.dto.BookUpdateRequest;
import com.examplenewstack.newstack.domain.book.exception.NoBooksFoundByISBNException;
import com.examplenewstack.newstack.domain.book.exception.NoBooksFoundByIdException;
import com.examplenewstack.newstack.domain.book.exception.NoBooksFoundException;
import com.examplenewstack.newstack.domain.book.repository.BookRepository;
import com.examplenewstack.newstack.domain.collection.Collection;
import com.examplenewstack.newstack.domain.collection.repository.CollectionRepository;
import com.examplenewstack.newstack.domain.employee.Employee;
import com.examplenewstack.newstack.domain.employee.repository.EmployeeRepository;
import com.examplenewstack.newstack.domain.loan.Loan;
import com.examplenewstack.newstack.domain.loan.repository.LoanRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookCrudService {

    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final CollectionRepository collectionRepository;
    private final EmployeeRepository employeeRepository;

    public BookCrudService(BookRepository bookRepository, CollectionRepository collectionRepository, EmployeeRepository employeeRepository, EmployeeRepository employeeRepositor, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.collectionRepository = collectionRepository;
        this.employeeRepository = employeeRepository;
        this.loanRepository = loanRepository;
    }


    //Função responsavel por registrar um livro
    @Transactional
    public Book register(BookRequest bookRequest) {
        Book newBook = bookRequest.tobook();
        // Ao criar um novo livro, a quantidade disponível é igual à total.
        newBook.setDisponibility_quantity(newBook.getTotal_quantity());
        return bookRepository.save(newBook);
    }

    @Transactional(readOnly = false) // Necessário para permitir a escrita (correção)
    public List<BookResponse> reportAllBooks() {
        List<Book> allBooks = bookRepository.findAll();

        return allBooks.stream().map(book -> {
            // 1. CALCULA a quantidade que DEVERIA estar disponível
            int activeLoans = loanRepository.countByBookAndActualReturnDateIsNull(book);
            int correctQuantity = book.getTotal_quantity() - activeLoans;

            // 2. VERIFICA se o valor no banco está desatualizado
            if (book.getDisponibility_quantity() != correctQuantity) {
                // 3. CORRIGE o valor no banco de dados
                book.setDisponibility_quantity(correctQuantity);
                bookRepository.save(book); // Salva a correção
            }

            // 4. RETORNA o DTO com o valor agora correto e atualizado
            return BookResponse.fromEntity(book);
        }).collect(Collectors.toList());
    }

    //Função responsavel por mostrar os livros com paginação

    public Page<BookResponse> getFilteredBooks(Integer page, Integer size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        Page<Book> foundBooks = bookRepository.findAll(pageRequest);
        
        return foundBooks.map(book -> {
            // 1. CALCULA a quantidade que DEVERIA estar disponível
            int activeLoans = loanRepository.countByBookAndActualReturnDateIsNull(book);
            int correctQuantity = book.getTotal_quantity() - activeLoans;

            // 2. VERIFICA se o valor no banco está desatualizado
            if (book.getDisponibility_quantity() != correctQuantity) {
                // 3. CORRIGE o valor no banco de dados
                book.setDisponibility_quantity(correctQuantity);
                bookRepository.save(book); // Salva a correção
            }

            // 4. RETORNA o DTO com o valor agora correto e atualizado
            return BookResponse.fromEntity(book);
        });
    }

    //Função responsavel por mostrar os livros pelo ID
    public BookResponse findByID(int bookID) {
        Book book = bookRepository.findById(bookID)
                .orElseThrow(NoBooksFoundByIdException::new);

        return BookResponse.fromEntity(book);
    }

    public BookResponseViewDetails findViewDetailsById(int id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NoBooksFoundException("Livro não encontrado"));
        return BookResponseViewDetails.fromEntity(book);
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
                .ok(BookResponse.fromEntity(updateBook));
    }

    public void updateBookByIdView(Integer id, BookUpdateRequest dto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com o ID: " + id));

        existingBook.setTitle(dto.title());
        existingBook.setISBN(dto.ISBN());
        existingBook.setCategory(dto.category());
        existingBook.setYear_publication(dto.year_publication());
        existingBook.setTotal_quantity(dto.total_quantity());
        existingBook.setAuthor(dto.author());
        existingBook.setDescription(dto.description());
        existingBook.setPublisher(dto.publisher());

        bookRepository.save(existingBook);
    }



    //Função responsavel por deletar todos os livros
    public void deleteAll() {

        if (bookRepository.count() == 0) {
            throw new NoBooksFoundException();
        }
        bookRepository.deleteAll();
    }

    //Função responsavel por deletar livro pelo ID
    public ResponseEntity<?> deleteByID(Integer bookID) {
        // Busca o livro ou lança exceção se não existir
        Book findBook = bookRepository.findById(bookID)
                .orElseThrow(NoBooksFoundByIdException::new);

        // Busca todos os empréstimos vinculados a esse livro
        List<Loan> loanList = loanRepository.findByBookId(bookID);

        // Se existirem empréstimos vinculados, desvincula o livro deles
        if (!loanList.isEmpty()) {
            for (Loan loan : loanList) {
                loan.setBook(null); // Remove a referência ao livro
            }
            loanRepository.saveAll(loanList); // Salva as mudanças nos empréstimos
        }

        // Agora exclui o livro
        bookRepository.delete(findBook);

        return ResponseEntity.ok().build();
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
