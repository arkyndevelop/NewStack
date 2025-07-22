package com.examplenewstack.newstack.domain.book.view;

import com.examplenewstack.newstack.domain.book.Book;
import com.examplenewstack.newstack.domain.book.dto.BookRequest;
import com.examplenewstack.newstack.domain.book.dto.BookResponse;
import com.examplenewstack.newstack.domain.book.service.BookCrudService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/register")
    public String registerBook(@ModelAttribute BookRequest book,
                               RedirectAttributes redirectAttributes) {
        try {
            bookCrudService.register(book);
            redirectAttributes.addFlashAttribute("successMessage", "Livro cadastrado com sucesso!");
            return "redirect:/books/reports";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao cadastrar livro: " + e.getMessage());
            return "redirect:/v1/books/register";
        }
    }

    @GetMapping("/reports")
    public ModelAndView reportAllBooks() {
        ModelAndView mav = new ModelAndView("reportBooks");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Set<String> userRoles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        List<String> adminRoles = Arrays.asList("ROLE_ADMIN", "ROLE_EMPLOYEE");
        boolean isAdminOrEmployee = userRoles.stream().anyMatch(adminRoles::contains);

        mav.addObject("viewType", isAdminOrEmployee ? "admin_employee" : "client");

        try {
            List<BookResponse> bookList = bookCrudService.reportAllBooks();
            mav.addObject("bookList", bookList);
        } catch (Exception e) {
            mav.addObject("bookList", Collections.emptyList());
            System.err.println("Erro ao buscar livros: " + e.getMessage());
        }

        return mav;
    }
}