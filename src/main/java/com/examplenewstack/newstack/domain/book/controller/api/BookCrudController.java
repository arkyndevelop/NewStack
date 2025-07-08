package com.examplenewstack.newstack.domain.book.controller.api;

import com.examplenewstack.newstack.domain.book.Book;
import com.examplenewstack.newstack.domain.book.dto.BookRequest;
import com.examplenewstack.newstack.domain.book.repository.BookRepository;
import com.examplenewstack.newstack.domain.book.service.BookCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@Tag(name = "Livros")
public class BookCrudController {

    private final BookCrudService service;
    private final BookRepository repository;

    public BookCrudController(BookCrudService service, BookRepository repository) {
        this.service = service;
        this.repository = repository;
    }


    //Controller responsável pelo endpoint de registar um livro
    @PostMapping("/register")
    public ResponseEntity<String> cadastrar(@RequestBody BookRequest bookRequest) {
        try {
            Book book=bookRequest.tobook();
            repository.save(book);
            return ResponseEntity.ok("Livro salvo com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao salvar livro: " + e.getMessage());
        }
    }

    //Função responsavel pelo endpoint de mostrar todos os livros
    @GetMapping("/reports/all")
    public ResponseEntity<?> reportAll() {
        return ResponseEntity.ok().body(service.reportAllBooks());
    }


    //Função responsavel pelo endpoint de mostrar um livro pelo ID
    @GetMapping("/reports/{id}")
    public ResponseEntity<?> report(
            @PathVariable int id
    ) {
        return ResponseEntity.ok().body(service.findByID(id));
    }

    //Função responsavel por atualizar um livro pelo ID
    @PutMapping("/update/{bookISBN}")
    public ResponseEntity<?> update(
            @RequestBody BookRequest requestDTO,
            @PathVariable String bookISBN
    ) {

        service.updateBook(requestDTO, bookISBN);
        return ResponseEntity.ok().build();
    }


    //Função por deletar todos os livros
    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteAll() {
        service.deleteAll();
        return ResponseEntity.ok().build();
    }

    //Função responsavel por deletar um livro pelo ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable int id
    ) {
        return ResponseEntity.ok().body(service.deleteByID(id));
    }


}
