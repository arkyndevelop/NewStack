package com.examplenewstack.newstack.employee.controller.api;


import com.examplenewstack.newstack.employee.service.DeleteAllEmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@Tag(name = "Funcionário")
public class DeleteAllEmployeeController {

    private final DeleteAllEmployeeService service;

    public DeleteAllEmployeeController(DeleteAllEmployeeService service) {
        this.service = service;
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteAllEmployees() {

        service.deleteAllEmployee();
        return ResponseEntity.ok().build();
    }
}
