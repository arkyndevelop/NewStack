package com.examplenewstack.newstack.domain.client.controller;

import com.examplenewstack.newstack.domain.client.dto.ClientRequest;
import com.examplenewstack.newstack.domain.client.exception.NoCustomersFoundByIdException;
import com.examplenewstack.newstack.domain.client.service.ClientCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/clients")
@Tag(name = "Cliente")
public class ClientCrudController {

    private final ClientCrudService clientCrudService;

    public ClientCrudController(ClientCrudService clientCrudService) {
        this.clientCrudService = clientCrudService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> clientRegisterController(@RequestBody @Valid ClientRequest clientRequest) {
        clientCrudService.registerClient(clientRequest);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String redirectUrl = "/v1/login"; // URL padrão para anônimos

        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            boolean isAdminOrEmployee = authorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch(role -> role.equals("ROLE_ADMIN") || role.startsWith("ROLE_"));

            // Se for admin ou funcionário, redireciona para a lista de clientes
            if (isAdminOrEmployee) {
                redirectUrl = "/v1/clients/report";
            }
        }

        // Retorna um JSON com a URL de redirecionamento
        return ResponseEntity.ok(Map.of("redirectUrl", redirectUrl));
    }

    @GetMapping("/reports/all")
    public ResponseEntity<?> reportsAllClientsController(){
        clientCrudService.findAllClients();
        return ResponseEntity.ok().build();
    }
}
