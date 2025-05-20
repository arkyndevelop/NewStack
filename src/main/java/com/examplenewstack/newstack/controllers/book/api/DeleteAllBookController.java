package com.examplenewstack.newstack.controllers.book.api;


import com.examplenewstack.newstack.service.book.DeleteAllBooksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
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
