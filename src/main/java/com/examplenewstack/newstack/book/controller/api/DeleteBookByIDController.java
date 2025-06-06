package com.examplenewstack.newstack.book.controller.api;

import com.examplenewstack.newstack.book.service.DeleteBookByIDService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@Tag(name = "Livros")
public class DeleteBookByIDController {

    private final DeleteBookByIDService deleteBookByIDService;

    public DeleteBookByIDController(DeleteBookByIDService deleteBookByIDService) {
        this.deleteBookByIDService = deleteBookByIDService;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable int id
    ){
        return ResponseEntity.ok().body(deleteBookByIDService.deleteByID(id));
    }
}
