package com.examplenewstack.newstack.domain.loan.view;

import com.examplenewstack.newstack.domain.book.Book;
import com.examplenewstack.newstack.domain.book.service.BookCrudService;
import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.domain.client.service.ClientCrudService;
import com.examplenewstack.newstack.domain.loan.dto.LoanRequest;
import com.examplenewstack.newstack.domain.loan.dto.LoanResponse;
import com.examplenewstack.newstack.domain.loan.service.LoanCrudService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String showRegisterLoanForm(Model model, Authentication authentication) {
        // Obter todos os livros (com a disponibilidade já calculada no service)
        List<Book> books = bookCrudService.reportAllBooks(); // Metodo que retorna Books com availableCopies
        model.addAttribute("books", books);
        // model.addAttribute("loanRequest", new LoanRequest()); // Se estiver usando um objeto de formulário

        // Lógica condicional baseada na role do usuário
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CLIENT"))) {
            // Se for um CLIENT, pegue o cliente logado
            String username = authentication.getName(); // Assumindo que o username é o identificador do cliente
            Client loggedInClient = clientCrudService.findByUsername(username); // Ou findByEmail, findById, etc.
            if (loggedInClient != null) {
                model.addAttribute("loggedInClient", loggedInClient);
            } else {
                model.addAttribute("error", "Não foi possível encontrar as informações do seu perfil de cliente.");
            }
        } else {
            // Para ADMIN, LIBRARIAN, etc., liste todos os clientes
            List<Client> clients = clientCrudService.findAllClients();
            model.addAttribute("clients", clients);
        }
}