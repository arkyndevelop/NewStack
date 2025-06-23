package com.examplenewstack.newstack.domain.book.controller.api;

import com.examplenewstack.newstack.domain.book.dto.BookRequestDTO;
import com.examplenewstack.newstack.domain.book.service.UpdateBookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@Tag(name = "Livros")
public class UpdateBookController {

    private final UpdateBookService updateBookService;

    public UpdateBookController(UpdateBookService updateBookService) {
        this.updateBookService = updateBookService;
    }

    @PutMapping("/update/{bookISBN}")
    public ResponseEntity<?> update(
            @RequestBody BookRequestDTO requestDTO,
            @PathVariable String bookISBN
    ){

        updateBookService.updateBook(requestDTO, bookISBN);
        return ResponseEntity.ok().build();
    }
}
