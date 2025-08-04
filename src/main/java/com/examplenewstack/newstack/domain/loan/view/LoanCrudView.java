package com.examplenewstack.newstack.domain.loan.view;

import com.examplenewstack.newstack.core.entity.User;
import com.examplenewstack.newstack.domain.book.dto.BookResponse;
import com.examplenewstack.newstack.domain.book.service.BookCrudService;
import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.domain.client.dto.ClientResponse;
import com.examplenewstack.newstack.domain.client.dto.ClientResponseProfileDetails;
import com.examplenewstack.newstack.domain.client.service.ClientCrudService;
import com.examplenewstack.newstack.domain.loan.dto.LoanRequest;
import com.examplenewstack.newstack.domain.loan.dto.LoanResponse;
import com.examplenewstack.newstack.domain.loan.service.LoanCrudService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/v1/loans")
public class LoanCrudView {

    private final LoanCrudService loanService;
    private final ClientCrudService clientCrudService;
    private final BookCrudService bookCrudService;

    public LoanCrudView(LoanCrudService loanService, ClientCrudService clientCrudService, BookCrudService bookCrudService) {
        this.loanService = loanService;
        this.clientCrudService = clientCrudService;
        this.bookCrudService = bookCrudService;
    }

    private void addUserRoleToModel(Authentication authentication, Model model) {
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            model.addAttribute("userRole", user.getRole());
        }
    }

    @GetMapping("/reports")
    public ModelAndView showLoanReports() {
        ModelAndView mav = new ModelAndView("reportLoans");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            mav.addObject("userRole", user.getRole());
        }

        boolean isClient = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_CLIENT"));
        mav.addObject("viewType", isClient ? "client" : "admin_employee");

        try {
            List<LoanResponse> loanList;
            if (isClient) {
                Client client = (Client) principal;
                loanList = loanService.reportByClientId(client.getId());
            } else {
                loanList = loanService.reportAll();
            }
            mav.addObject("loanList", loanList);
        } catch (Exception e) {
            mav.addObject("loanList", Collections.emptyList());
            mav.addObject("error", e.getMessage());
        }

        return mav;
    }

    @GetMapping("/register")
    public String showRegisterLoanForm(Model model, Authentication authentication) {
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            model.addAttribute("userRole", user.getRole());
        }

        List<BookResponse> availableBooks = bookCrudService.reportAllBooks();
        model.addAttribute("books", availableBooks);

        boolean isClient = authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_CLIENT"));
        model.addAttribute("isClient", isClient);

        if (isClient) {
            try {
                ClientResponseProfileDetails loggedInClient = clientCrudService.getAuthenticatedClientProfile();
                model.addAttribute("loggedInClient", loggedInClient);
            } catch (Exception e) {
                model.addAttribute("error", "Não foi possível carregar as informações do seu perfil.");
            }
        } else {
            List<ClientResponse> allClients = clientCrudService.findAllClients();
            model.addAttribute("clients", allClients);
        }

        return "registerLoan";
    }

    @PostMapping("/register")
    public String registerLoan(LoanRequest loanRequest, RedirectAttributes redirectAttributes) {
        try {
            loanService.register(loanRequest);
            redirectAttributes.addFlashAttribute("successMessage", "Empréstimo registrado com sucesso!");
            return "redirect:/v1/loans/reports";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao registrar empréstimo: " + e.getMessage());
            return "redirect:/v1/loans/register";
        }
    }

    @PostMapping("/return/{id}")
    public String returnLoan(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            loanService.confirmReturn(id);
            redirectAttributes.addFlashAttribute("successMessage", "Devolução do livro confirmada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao confirmar a devolução: " + e.getMessage());
        }
        return "redirect:/v1/loans/reports";
    }
}
