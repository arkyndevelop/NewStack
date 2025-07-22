package com.examplenewstack.newstack.domain.book.controller.view;


import com.examplenewstack.newstack.domain.book.Book;
import com.examplenewstack.newstack.domain.book.dto.BookResponseViewDetails;
import com.examplenewstack.newstack.domain.book.dto.BookUpdateRequest;
import com.examplenewstack.newstack.domain.book.service.BookCrudService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/books")
public class UpdateBookByID {

    private final BookCrudService bookCrudService;

    public UpdateBookByID(BookCrudService bookCrudService) {
        this.bookCrudService = bookCrudService;
    }

    // GET: Carrega a página de edição do livro
    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable Integer id, Model model) {
        BookResponseViewDetails book = bookCrudService.findViewDetailsById(id); // ou método equivalente
        model.addAttribute("book", book);
        return new ModelAndView("book/edit"); // Nome do template Thymeleaf
    }


    // POST: Atualiza o livro e redireciona para a listagem (/v1/books/reports)
    @PostMapping("/{id}/edit")
    public String updateBook(
            @PathVariable Integer id,
            @Valid BookUpdateRequest request
    ) {
        bookCrudService.updateBookByIdView(id, request);
        return "redirect:/v1/books/reports"; // Redireciona após salvar
    }
}

