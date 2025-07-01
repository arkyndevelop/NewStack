package com.examplenewstack.newstack.domain.book.controller.api;

import com.examplenewstack.newstack.domain.book.dto.BookRequestDTO;
import com.examplenewstack.newstack.domain.book.service.BookCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@Tag(name = "Livros")
public class BookCrudController {

    private final BookCrudService bookCrudService;

    public BookCrudController(BookCrudService bookCrudService) {
        this.bookCrudService = bookCrudService;
    }


    //Controller responsavel pelo endpoint de registar um livro
    @PostMapping("/register")
    public ResponseEntity<?> registerBook(
            @RequestBody @Valid BookRequestDTO bookDTO
    ) {
        return ResponseEntity.ok().body(bookCrudService.register(bookDTO, bookDTO.ISBN(), bookDTO.collectionId(), bookDTO.employeeId()));
    }

    //Função responsavel pelo endpoint de mostrar todos os livros
    @GetMapping("/reports/all")
    public ResponseEntity<?> reportAll(){
        return ResponseEntity.ok().body(bookCrudService.reportAllBooks());
    }


//Função responsavel pelo endpoint de mostrar um livro pelo ID
@GetMapping("/reports/{id}")
public ResponseEntity<?> report(
        @PathVariable int id
){
    return ResponseEntity.ok().body(bookCrudService.findByID(id));
}

//Função responsavel por atualizar um livro pelo ID
@PutMapping("/update/{bookISBN}")
public ResponseEntity<?> update(
        @RequestBody BookRequestDTO requestDTO,
        @PathVariable String bookISBN
){

    bookCrudService.updateBook(requestDTO, bookISBN);
    return ResponseEntity.ok().build();
}


//Função por deletar todos os livros
@DeleteMapping("/delete/all")
public ResponseEntity<?> deleteAll(){
    bookCrudService.deleteAll();
    return ResponseEntity.ok().build();
}

//Função responsavel por deletar um livro pelo ID
@DeleteMapping("/delete/{id}")
public ResponseEntity<?> delete(
        @PathVariable int id
){
    return ResponseEntity.ok().body(bookCrudService.deleteByID(id));
}


}
