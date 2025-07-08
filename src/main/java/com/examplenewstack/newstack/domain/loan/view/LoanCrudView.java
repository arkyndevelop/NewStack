package com.examplenewstack.newstack.domain.loan.view;

import com.examplenewstack.newstack.domain.book.service.BookCrudService;
import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.domain.client.service.ClientCrudService;
import com.examplenewstack.newstack.domain.loan.dto.LoanRequest;
import com.examplenewstack.newstack.domain.loan.dto.LoanResponse;
import com.examplenewstack.newstack.domain.loan.service.LoanCrudService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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


    @GetMapping("/reports")
    public ModelAndView showLoanReports() {
        ModelAndView mav = new ModelAndView("reportLoans");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

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
    public ModelAndView showRegisterLoanForm() {
        ModelAndView mav = new ModelAndView("registerLoan");
        try {
            // Adiciona a lista de todos os clientes e livros ao modelo
            mav.addObject("clients", clientCrudService.findAllClients());
            mav.addObject("books", bookCrudService.reportAllBooks());
            mav.addObject("loanRequest", new LoanRequest(null, null, 0, 0)); // Objeto para o form
        } catch (Exception e) {
            mav.addObject("error", "Não foi possível carregar os dados para o formulário.");
        }
        return mav;
    }
}