package com.examplenewstack.newstack.controller.bookController;

import com.examplenewstack.newstack.entity.dto.bookdto.BookDTO;
import com.examplenewstack.newstack.entity.librarie.book.Book;
import com.examplenewstack.newstack.repository.BookRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/v1")
public class CreateBook {
    // Injeção de dependência do repositório de livros
    private final BookRepository bookRepository;
    // Construtor para injeção de dependência
    public CreateBook(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/book")
    public ModelAndView registerScreenBook(HttpServletRequest request, Model model){
        return new ModelAndView("admMaster");
    }

    @PostMapping("/add")
    public ResponseEntity<?> registerBook(@Valid @RequestBody BookDTO bookDTO, BindingResult result){
        //verifica erros
        if(result.hasErrors()){
            // Captura a mensagem de erro
            String errorMessage = result.getFieldError().getDefaultMessage();

            return ResponseEntity.badRequest().body((errorMessage));
        }

        Book book = bookDTO.toBook();
        bookRepository.save(book);

        return ResponseEntity.ok().build();


    }
}
