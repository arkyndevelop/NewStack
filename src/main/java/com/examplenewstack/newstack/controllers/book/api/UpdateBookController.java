package com.examplenewstack.newstack.controllers.book.api;

import com.examplenewstack.newstack.dtos.book.BookDTO;
import com.examplenewstack.newstack.service.book.UpdateBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class UpdateBookController {

    private final UpdateBookService updateBookService;

    public UpdateBookController(UpdateBookService updateBookService) {
        this.updateBookService = updateBookService;
    }

    @PutMapping("/update/{bookISBN}")
    public ResponseEntity<?> update(
            @RequestBody BookDTO bookDTO,
            @PathVariable String bookISBN
    ){

        updateBookService.updateBook(bookDTO, bookISBN);
        return ResponseEntity.ok().build();
    }
}
