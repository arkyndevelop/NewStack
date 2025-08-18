package com.examplenewstack.newstack.domain.admin.view;

import com.examplenewstack.newstack.domain.admin.dto.AdminRequest;
import com.examplenewstack.newstack.domain.admin.dto.AdminResponse;
import com.examplenewstack.newstack.domain.admin.service.AdmCrudService;
import com.examplenewstack.newstack.domain.employee.dto.EmployeeResponse;
import com.examplenewstack.newstack.domain.employee.dto.EmployeeResponseProfileDetails;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/v1/admins")
public class AdminCrudView {

    private final AdmCrudService service;

    public AdminCrudView(AdmCrudService service) {
        this.service = service;
    }

    @GetMapping("/register")
    public String showRegisterAdminForm(){
        return "registerAdmin";
    }

    @GetMapping("/report")
    public ModelAndView showAdminsReport(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        try {
            Page<AdminResponse> adminPage = service.getFilteredAdmins(page, size, orderBy, direction);
            ModelAndView mav = new ModelAndView("reportAdm");
            mav.addObject("adminPage", adminPage);
            mav.addObject("adminList", adminPage.getContent());

            // Adicionar informações de paginação para o template
            mav.addObject("currentPage", page);
            mav.addObject("totalPages", adminPage.getTotalPages());
            mav.addObject("totalElements", adminPage.getTotalElements());
            mav.addObject("size", size);
            mav.addObject("orderBy", orderBy);
            mav.addObject("direction", direction);

            return mav;
        } catch (Exception e) {
            ModelAndView mav = new ModelAndView("reportAdm");
            mav.addObject("admList", List.of());
            mav.addObject("adminPage", null);
            return mav;
        }
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

//    @GetMapping("/profile/{id}")
//    public ModelAndView showAdminProfileById(@PathVariable("id") int id) {
//        AdminResponse AdminDetails = service.getAdminProfileById(id);
//        ModelAndView mav = new ModelAndView("profileAdm");
//        mav.addObject("adm", employeeDetails);
//        return mav;
//    }
}
