package com.examplenewstack.newstack.controller.bookController;



import com.examplenewstack.newstack.model.dto.bookDTO.BookDTO;
import com.examplenewstack.newstack.model.librarie.book.Book;
import com.examplenewstack.newstack.repository.BookRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CreateBook {
    // Injeção de dependência do repositório de livros
    private final BookRepository bookRepository;
    // Construtor para injeção de dependência
    public CreateBook(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/book")
    public ModelAndView registerScreenBook(HttpServletRequest request, Model model){
        ModelAndView modelAndView = new ModelAndView("registerBook");
        return modelAndView;
    }

    @PostMapping("/registerBook/new")
    public ResponseEntity<?> registerBook(@Valid @RequestBody com.examplenewstack.newstack.model.dto.bookDTO.BookDTO bookDTO, BindingResult result){
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
