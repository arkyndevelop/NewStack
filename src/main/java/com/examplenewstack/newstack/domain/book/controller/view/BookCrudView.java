package com.examplenewstack.newstack.domain.book.controller.view;

import com.examplenewstack.newstack.domain.book.Book;
import com.examplenewstack.newstack.domain.book.dto.BookResponse;
import com.examplenewstack.newstack.domain.book.service.BookCrudService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
        // 1. Busca a lista de livros do serviço
        List<BookResponse> bookList = bookCrudService.reportAllBooks();

        // 2. Cria o objeto ModelAndView, apontando para o arquivo HTML
        ModelAndView modelAndView = new ModelAndView("reportBooks");

        // 3. Adiciona a lista de clientes ao modelo com o nome "bookList"
        modelAndView.addObject("bookList", bookList);

        // 4. Retorna o modelo com os dados para a view
        return modelAndView;
    }
}