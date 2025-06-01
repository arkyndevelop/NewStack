package com.examplenewstack.newstack.employee.controller.api;

import com.examplenewstack.newstack.employee.dto.EmployeeRequestDTO;
import com.examplenewstack.newstack.employee.service.UpdateEmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@Tag(name = "Funcionário")
public class UpdateEmployeeController {
    private final UpdateEmployeeService service;

    public UpdateEmployeeController(UpdateEmployeeService service) {
        this.service = service;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClient(
            @RequestBody @Valid EmployeeRequestDTO request,
            @RequestParam Long id
    ) {

        service.updateEmployee(request, id);
        return ResponseEntity.ok().build();
    }
}
