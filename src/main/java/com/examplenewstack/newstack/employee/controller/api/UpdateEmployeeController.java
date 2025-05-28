package com.examplenewstack.newstack.employee.controller.api;

import com.examplenewstack.newstack.employee.dto.EmployeeRequestDTO;
import com.examplenewstack.newstack.employee.service.UpdateEmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@Tag(name = "Funcionário")
public class UpdateEmployeeController {
    private final UpdateEmployeeService updateEmployeeService;

    public UpdateEmployeeController(UpdateEmployeeService updateEmployeeService){
        this.updateEmployeeService = updateEmployeeService;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClient(
            @RequestBody EmployeeRequestDTO employeeDTO,
            @RequestParam Long id
    ){

            updateEmployeeService.updateEmployee(employeeDTO, id);
            return ResponseEntity.ok().build();


    }
}
