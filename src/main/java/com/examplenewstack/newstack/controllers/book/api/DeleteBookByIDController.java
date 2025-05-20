package com.examplenewstack.newstack.controllers.book.api;

import com.examplenewstack.newstack.service.book.DeleteBookByIDService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class DeleteBookByIDController {

    private final DeleteBookByIDService deleteBookByIDService;

    public DeleteBookByIDController(DeleteBookByIDService deleteBookByIDService) {
        this.deleteBookByIDService = deleteBookByIDService;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id
    ){
        return ResponseEntity.ok().body(deleteBookByIDService.deleteByID(id));
    }
}
