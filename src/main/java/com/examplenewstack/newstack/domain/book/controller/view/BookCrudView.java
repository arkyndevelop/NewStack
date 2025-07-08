package com.examplenewstack.newstack.domain.book.controller.view;

import com.examplenewstack.newstack.domain.book.Book;
import com.examplenewstack.newstack.domain.book.dto.BookResponse;
import com.examplenewstack.newstack.domain.book.service.BookCrudService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        ModelAndView mav = new ModelAndView("reportBooks");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 1. Obtenha as roles do usuário logado de forma clara
        Set<String> userRoles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        // 2. Defina quais roles podem ver a versão "admin"
        List<String> adminRoles = Arrays.asList("ROLE_ADMIN", "ROLE_EMPLOYEE");

        // 3. Verifique se o usuário possui QUALQUER UMA das roles de admin
        boolean isAdminOrEmployee = userRoles.stream().anyMatch(adminRoles::contains);

        if (isAdminOrEmployee) {
            mav.addObject("viewType", "admin_employee");
        } else {
            mav.addObject("viewType", "client");
        }

        try {
            List<BookResponse> bookList = bookCrudService.reportAllBooks();
            mav.addObject("bookList", bookList);
        } catch (Exception e) {
            mav.addObject("bookList", Collections.emptyList());
            // É uma boa prática logar o erro
            // log.error("Erro ao buscar livros para o relatório", e);
            System.err.println("Erro ao buscar livros: " + e.getMessage());
        }

        return mav;
    }

}