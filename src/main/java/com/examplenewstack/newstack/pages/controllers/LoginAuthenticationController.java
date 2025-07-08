package com.examplenewstack.newstack.pages.controllers;

import com.examplenewstack.newstack.core.entity.User;
import com.examplenewstack.newstack.infra.service.TokenService;
import com.examplenewstack.newstack.pages.dto.login.LoginRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/auth")
public class LoginAuthenticationController {

    private final AuthenticationManager manager;
    private final TokenService service;

    public LoginAuthenticationController(AuthenticationManager manager, TokenService service) {
        this.manager = manager;
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request, HttpServletResponse response){
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        var auth = this.manager.authenticate(usernamePassword);

        var token = service.generatedToken((User) auth.getPrincipal());

        // CORREÇÃO: Cria um cookie HTTP-Only para armazenar o token
        Cookie cookie = new Cookie("jwt_token", token);
        cookie.setPath("/"); // Disponível para toda a aplicação
        cookie.setHttpOnly(true); // Protege contra ataques XSS (o cookie não pode ser lido por JavaScript)
        cookie.setMaxAge(2 * 60 * 60); // Expira em 2 horas (mesmo tempo do token)
        // Em produção, adicione: cookie.setSecure(true); para exigir HTTPS

        // Adiciona o cookie na resposta que vai para o navegador
        response.addCookie(cookie);

        // Retorna uma resposta de sucesso vazia. O cliente irá redirecionar.
        return ResponseEntity.ok().build();
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletResponse response) {
        // Cria um cookie com o mesmo nome, mas com valor nulo e tempo de vida 0 para removê-lo
        Cookie cookie = new Cookie("jwt_token", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        // Limpa o contexto de segurança
        SecurityContextHolder.clearContext();

        return new ModelAndView("redirect:/v1/login?logout");
    }
}