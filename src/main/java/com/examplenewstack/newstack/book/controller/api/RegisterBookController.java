package com.examplenewstack.newstack.book.controller.api;

import com.examplenewstack.newstack.book.Book;
import com.examplenewstack.newstack.book.dto.BookRequestDTO;
import com.examplenewstack.newstack.book.service.RegisterBookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/books")
@Tag(name = "Livros")
public class RegisterBookController {

<<<<<<< HEAD
    private final RegisterBookService registerBookService;

    public RegisterBookController(RegisterBookService registerBookService) {
        this.registerBookService = registerBookService;
    }

    @PostMapping(value = "/register", consumes = {"multipart/form-data"})
    public ResponseEntity<?> registerBook(
            @RequestPart("book") @Valid BookRequestDTO bookDTO,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem
    ) {
        try {
            // Chama o serviço passando o DTO e o arquivo da imagem
            registerBookService.register(bookDTO,imagem);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
=======
//    private final RegisterBookService registerBookService;
//
//    public RegisterBookController(RegisterBookService registerBookService) {
//        this.registerBookService = registerBookService;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<?> registerBook(
//            @RequestBody @Valid BookRequestDTO bookDTO
//    ){
//        try {
//            Book book = registerBookService.register(bookDTO, bookDTO.ISBN(), bookDTO.collectionId(), bookDTO.employeeId());
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
>>>>>>> 1efdc77e704b013fc27c0253f528a7ef8939012e
}
