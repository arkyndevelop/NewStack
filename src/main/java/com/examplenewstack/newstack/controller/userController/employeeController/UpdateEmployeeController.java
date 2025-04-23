package com.examplenewstack.newstack.controller.userController.employeeController;

import com.examplenewstack.newstack.model.employee.Employee;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1")
public class UpdateEmployeeController {


    private final UpdateEmployeeController updateEmployeeController;



    public UpdateEmployeeController(@Lazy  UpdateEmployeeController updateEmployeeController) {
        this.updateEmployeeController = updateEmployeeController;
    }

    @PostMapping("/employee/update/{cpf}")
    public String updateEmployee(@PathVariable String cpf, @ModelAttribute Employee employee){
        try{
            updateEmployeeController.updateEmployee(cpf, employee);
            return "redirect:/reports";
        } catch (Exception e){
            return "redirect:/reports" + e.getMessage().replace(" ", "+");
        }
    }
}
