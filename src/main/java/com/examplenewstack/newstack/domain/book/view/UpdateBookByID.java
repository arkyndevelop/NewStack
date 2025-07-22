package com.examplenewstack.newstack.domain.book.view;

import com.examplenewstack.newstack.domain.book.dto.BookResponseViewDetails;
import com.examplenewstack.newstack.domain.book.dto.BookUpdateRequest;
import com.examplenewstack.newstack.domain.book.service.BookCrudService;
import jakarta.validation.Valid;
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
    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Integer id, Model model) {
        BookResponseViewDetails book = bookCrudService.findViewDetailsById(id);// ou metodo equivalente
        model.addAttribute("id", id);
        model.addAttribute("book", book);
        return new ModelAndView("edit-book"); // Nome do template Thymeleaf
    }


    // POST: Atualiza o livro e redireciona para a listagem (/v1/books/reports)
    @PostMapping("/edit/{id}")
    public String updateBook(
            @PathVariable Integer id,
            @Valid BookUpdateRequest request
    ) {
        bookCrudService.updateBookByIdView(id, request);
        return "redirect:/v1/books/reports"; // Redireciona após salvar
    }
}

