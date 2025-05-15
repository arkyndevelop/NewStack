package com.examplenewstack.newstack.controllers.user.employee.api;

import com.examplenewstack.newstack.dtos.employee.EmployeeDTO;
import com.examplenewstack.newstack.service.user.employee.UpdateEmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class UpdateEmployeeController {
    private final UpdateEmployeeService updateEmployeeService;

    public UpdateEmployeeController(UpdateEmployeeService updateEmployeeService){
        this.updateEmployeeService = updateEmployeeService;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClient(
            @RequestBody EmployeeDTO employeeDTO,
            @RequestParam Long id
    ){

            updateEmployeeService.updateEmployee(employeeDTO, id);
            return ResponseEntity.ok().build();


    }
}
