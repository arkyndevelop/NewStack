package com.examplenewstack.newstack.domain.employee.view;

import com.examplenewstack.newstack.domain.client.exception.NoCustomersFoundByIdException;
import com.examplenewstack.newstack.domain.employee.dto.EmployeeRequest;
import com.examplenewstack.newstack.domain.employee.dto.EmployeeResponse;
import com.examplenewstack.newstack.domain.employee.dto.EmployeeResponseProfileDetails;
import com.examplenewstack.newstack.domain.employee.exception.EmployeersRegisteredDataException;
import com.examplenewstack.newstack.domain.employee.service.EmployeeCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/v1/employees")
public class EmployeeCrudView {

    private final EmployeeCrudService employeeService;

    @Autowired
    public EmployeeCrudView(EmployeeCrudService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/register")
    public ModelAndView showRegisterView() {
        return new ModelAndView("registerEmployee");
    }

    @PostMapping("/register")
    public String handleRegistration(@ModelAttribute EmployeeRequest employeeRequest, RedirectAttributes redirectAttributes) {
        try {
            employeeService.registerEmployee(employeeRequest);
            redirectAttributes.addFlashAttribute("message", "Funcionário cadastrado com sucesso!");
            return "redirect:/v1/employees/report";
        } catch (EmployeersRegisteredDataException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/v1/employees/register";
        }
    }

    @GetMapping("/report")
    public ModelAndView showEmployeesReport() {
        List<EmployeeResponse> employeeList = employeeService.findAllEmployees();
        ModelAndView mav = new ModelAndView("reportEmployee");
        mav.addObject("employeeList", employeeList);
        return mav;
    }

    @GetMapping("/profile/{id}")
    public ModelAndView showEmployeeProfileById(@PathVariable("id") int id) {
        EmployeeResponseProfileDetails employeeDetails = employeeService.getEmployeeProfileById(id);
        ModelAndView mav = new ModelAndView("profileEmployee");
        mav.addObject("employee", employeeDetails);
        return mav;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable("id") int id) {
        EmployeeResponseProfileDetails employeeDetails = employeeService.getEmployeeProfileById(id);
        ModelAndView mav = new ModelAndView("editEmployee");
        mav.addObject("employee", employeeDetails);
        return mav;
    }

    @PostMapping("/edit/{id}")
    public String handleAdminUpdate(@PathVariable("id") int id,
                                    @ModelAttribute EmployeeRequest employeeRequest,
                                    RedirectAttributes redirectAttributes) {
        try {
            employeeService.updateEmployeeByAdmin(id, employeeRequest);
            redirectAttributes.addFlashAttribute("message", "Funcionário atualizado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao atualizar funcionário: " + e.getMessage());
        }
        return "redirect:/v1/employees/report";
    }

    @PostMapping("/delete/{id}")
    public String handleDeleteEmployee (@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            employeeService.deleteEmployeeById(id);
            redirectAttributes.addFlashAttribute("message", "Colaborador excluído com sucesso!");
        } catch (NoCustomersFoundByIdException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocorreu um erro inesperado ao tentar excluir o cliente.");
        }
        return "redirect:/v1/employees/report";
    }
}