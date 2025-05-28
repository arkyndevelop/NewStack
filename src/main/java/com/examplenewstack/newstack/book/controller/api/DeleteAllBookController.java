package com.examplenewstack.newstack.book.controller.api;


import com.examplenewstack.newstack.book.service.DeleteAllBooksService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@Tag(name = "Livros")
public class DeleteAllBookController {

    private final DeleteAllBooksService deleteAllBookService;

    public DeleteAllBookController(DeleteAllBooksService deleteAllBookService) {
        this.deleteAllBookService = deleteAllBookService;
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteAll(){
        deleteAllBookService.deleteAll();
        return ResponseEntity.ok().build();
    }
}
