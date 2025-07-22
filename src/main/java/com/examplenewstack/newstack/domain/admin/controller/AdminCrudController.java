package com.examplenewstack.newstack.domain.admin.controller;

import com.examplenewstack.newstack.domain.admin.dto.AdminRequest;
import com.examplenewstack.newstack.domain.admin.service.AdmCrudService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/admins")
public class AdminCrudController {

    private final AdmCrudService service;

    public AdminCrudController(AdmCrudService service) {
        this.service = service;
    }

    @PostMapping
    public ModelAndView create(@Valid AdminRequest adminRequest, RedirectAttributes redirectAttributes) {
        try {
            service.registerAdm(adminRequest);
            redirectAttributes.addFlashAttribute("successMessage", "Administrador cadastrado com sucesso!");
            return new ModelAndView("redirect:/v1/home"); // Redireciona para a home
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao cadastrar administrador: " + e.getMessage());
            return new ModelAndView("redirect:/v1/admins/register"); // Volta para o formulário em caso de erro
        }
    }
}
