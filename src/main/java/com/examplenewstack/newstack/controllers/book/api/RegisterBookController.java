package com.examplenewstack.newstack.controllers.book.api;

import com.examplenewstack.newstack.dtos.book.BookDTO;
import com.examplenewstack.newstack.entity.book.Book;
import com.examplenewstack.newstack.service.book.RegisterBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class RegisterBookController {

    private final RegisterBookService registerBookService;

    public RegisterBookController(RegisterBookService registerBookService) {
        this.registerBookService = registerBookService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerBook(
            @RequestBody BookDTO bookDTO
    ){
        try {
            Book book = registerBookService.register(bookDTO, bookDTO.getISBN());
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
