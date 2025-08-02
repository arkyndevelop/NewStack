package com.examplenewstack.newstack.domain.admin.controller;

import com.examplenewstack.newstack.domain.admin.dto.AdminRequest;
import com.examplenewstack.newstack.domain.admin.service.AdmCrudService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public ModelAndView create(@Valid @ModelAttribute AdminRequest adminRequest, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // Verifica se há erros de validação (como CPF inválido)
        if (bindingResult.hasErrors()) {
            // Pega a primeira mensagem de erro e a envia de volta para o formulário
            String defaultMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
            redirectAttributes.addFlashAttribute("errorMessage", defaultMessage);
            return new ModelAndView("redirect:/v1/admins/register"); // Volta para o formulário
        }

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
