package com.examplenewstack.newstack.domain.client.controller;

import com.examplenewstack.newstack.domain.client.dto.ClientRequest;
import com.examplenewstack.newstack.domain.client.dto.ClientResponse;
import com.examplenewstack.newstack.domain.client.exception.NoCustomersFoundByIdException;
import com.examplenewstack.newstack.domain.client.service.ClientCrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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

    //Função responsável pelo endpoint de mostrar clientes com paginação
    @GetMapping
    @Operation(summary = "Listar clientes usando filtros com paginação")
    public ResponseEntity<Page<ClientResponse>> getFilteredClients(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok().body(clientCrudService.getFilteredClients(page, size, orderBy, direction));
    }
}
