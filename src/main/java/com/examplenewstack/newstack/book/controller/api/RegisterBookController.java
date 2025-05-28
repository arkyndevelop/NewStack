package com.examplenewstack.newstack.book.controller.api;

import com.examplenewstack.newstack.book.dto.BookDTO;
import com.examplenewstack.newstack.book.Book;
import com.examplenewstack.newstack.book.service.RegisterBookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@Tag(name = "Livros")
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
