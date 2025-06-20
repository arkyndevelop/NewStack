package com.examplenewstack.newstack.book.service;

import com.examplenewstack.newstack.book.Book;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name = "Livros")
public class BookListController {

    private final RegisterBookService registerBookService;

    public BookListController(RegisterBookService registerBookService) {
        this.registerBookService = registerBookService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Book>> listBooks() {
        List<Book> books = registerBookService.listAll();
        return ResponseEntity.ok(books);
    }
}
