package com.examplenewstack.newstack.pages.controllers;

import com.examplenewstack.newstack.core.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

// Classe responsável por realizar o filtro das roles dos usuários que realizam o acesso,
// onde dessa forma faz com que seja redirecionado diretamente para endpoints que ele terá permissão!

@Controller
@RequestMapping("/v1")
public class HomeController {

    @GetMapping("/home")
    public String homeRedirect(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Se não houver autenticação, redireciona para o login
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/v1/login";
        }

        // Recebe a identidade do usuário autenticado
        Object principal = authentication.getPrincipal();

        // Verificamos se o principal é uma instância da sua classe User
        if (principal instanceof User user) {
            String role = user.getRole();

            System.out.println("Usuário autenticado: " + user.getName()); // Informa o Username
            System.out.println("Permissão encontrada: " + role);

            // Lógica de redirecionamento baseada na string da role
            if ("ADMIN".equals(role)) {
                System.out.println("Redirecionando para /homeAdm");
                return "redirect:/v1/home/admin";
            }
            if ("EMPLOYEE".equals(role) || "LIBRARY_ASSISTANT".equals(role) || "RECEPTIONIST".equals(role) || "LIBRARIAN".equals(role)) {
                System.out.println("Redirecionando para /homeEmployee");
                return "redirect:/v1/home/employee";
            }
            if ("CLIENT".equals(role)) {
                System.out.println("Redirecionando para /homeClient");
                return "redirect:/v1/home/client";
            }
        }

        // Se, por algum motivo, não tiver nenhuma role ou o principal não for um User, desloga.
        request.getSession().invalidate();
        return "redirect:/v1/login";
    }

    @GetMapping("/home/admin")
    public ModelAndView showAdminHome() {
        return new ModelAndView("homeAdm");
    }

    @GetMapping("/home/employee")
    public ModelAndView showEmployeeHome() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Verificação de autenticação
        if (authentication != null && authentication.isAuthenticated()) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                String role = authority.getAuthority();

                // Verificação da Roles do Usuário Employee
                if (role.equals("ROLE_LIBRARIAN") || role.equals("ROLE_LIBRARY_ASSISTANT") || role.equals("ROLE_RECEPTIONIST") || role.equals("ROLE_EMPLOYEE")) {
                    return new ModelAndView("homeEmployee");
                }
            }
        }
        return new ModelAndView("redirect:/v1/home");
    }

    @GetMapping("/home/client")
    public ModelAndView showClientHome() {
        ModelAndView mav = new ModelAndView("homeClient");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Pega o nome do usuário autenticado para a saudação
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            mav.addObject("clientName", user.getName());
        } else {
            mav.addObject("clientName", "Cliente");
        }
        return mav;
    }
}