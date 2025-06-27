package com.examplenewstack.newstack.controllers.home;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
@RequestMapping("/v1")
public class HomeController {

    @GetMapping("/home")
    public String homeRedirect(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection authorities = authentication.getAuthorities();

        // Adicionando logs para depuração. Verifique o console da sua aplicação!
        System.out.println("Usuário autenticado: " + authentication.getName());
        System.out.println("Permissões encontradas: " + authorities);

        // Lógica de redirecionamento com prioridade
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            System.out.println("Redirecionando para /homeAdm");
            return "redirect:/v1/home/admin"; // Usando redirect para URL específica
        }
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_EMPLOYEE"))) {
            System.out.println("Redirecionando para /homeEmployee");
            return "redirect:/v1/home/employee";
        }
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_CLIENT"))) {
            System.out.println("Redirecionando para /homeClient");
            return "redirect:/v1/home/client";
        }

        // Se, por algum motivo, não tiver nenhuma role, desloga o usuário
        request.getSession().invalidate();
        return "redirect:/login?error=unauthorized";
    }

    // Criamos endpoints separados para cada home page
    @GetMapping("/home/admin")
    public ModelAndView showAdminHome() {
        return new ModelAndView("homeAdm");
    }

    @GetMapping("/home/employee")
    public ModelAndView showEmployeeHome() {
        return new ModelAndView("homeEmployee");
    }

    @GetMapping("/home/client")
    public ModelAndView showClientHome() {
        return new ModelAndView("homeClient");
    }
}