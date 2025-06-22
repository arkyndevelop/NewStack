package com.examplenewstack.newstack.book.controller.api;

import com.examplenewstack.newstack.book.Book;
import com.examplenewstack.newstack.book.dto.BookRequestDTO;
import com.examplenewstack.newstack.book.service.RegisterBookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
            @RequestBody @Valid BookRequestDTO bookDTO
    ) {
        return ResponseEntity.ok().body(registerBookService.register(bookDTO, bookDTO.ISBN(), bookDTO.collectionId(), bookDTO.employeeId()));
    }
}
