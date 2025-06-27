package com.examplenewstack.newstack.domain.employee.controller.api;

import com.examplenewstack.newstack.domain.employee.dto.EmployeeRequest;
import com.examplenewstack.newstack.domain.employee.service.UpdateEmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@Tag(name = "Funcionário")
public class UpdateEmployeeController {
    private final UpdateEmployeeService service;

    public UpdateEmployeeController(UpdateEmployeeService service) {
        this.service = service;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClient(
            @RequestBody @Valid EmployeeRequest request,
            @RequestParam int id
    ) {

        service.updateEmployee(request, id);
        return ResponseEntity.ok().build();
    }
}
