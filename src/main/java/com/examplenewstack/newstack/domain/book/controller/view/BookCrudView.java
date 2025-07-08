package com.examplenewstack.newstack.domain.book.controller.view;

import com.examplenewstack.newstack.domain.book.Book;
import com.examplenewstack.newstack.domain.book.dto.BookResponse;
import com.examplenewstack.newstack.domain.book.service.BookCrudService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/v1/books") // Mapeamento centralizado para /books para consistência
public class BookCrudView {

    private final BookCrudService bookCrudService;

    // Injeção de dependência do serviço
    public BookCrudView(BookCrudService bookCrudService) {
        this.bookCrudService = bookCrudService;
    }

    // Mostra a tela de cadastro (view)
    @GetMapping("/register")
    public String registerScreen(Model model) {
        model.addAttribute("book", new Book());
        return "registerBook";
    }

    // Mostra a tela com todos os livros
    @GetMapping("/reports")
    public ModelAndView reportAllBooks() {
        ModelAndView modelAndView = new ModelAndView("reportBooks");
        try {
            // Busca a lista de livros do serviço
            List<BookResponse> bookList = bookCrudService.reportAllBooks();
            modelAndView.addObject("bookList", bookList);
        } catch (Exception e) {
            // Se ocorrer uma exceção (como a de 'nenhum livro encontrado'),
            // adiciona uma lista vazia para a página ser renderizada
            modelAndView.addObject("bookList", Collections.emptyList());
            // Opcional: logar o erro ou adicionar uma mensagem específica
            System.err.println("Erro ao buscar livros: " + e.getMessage());
        }
        return modelAndView;
    }

}