package com.examplenewstack.newstack.domain.employee.controller;

import com.examplenewstack.newstack.domain.employee.dto.EmployeeRequest;
import com.examplenewstack.newstack.domain.employee.dto.EmployeeResponse;
import com.examplenewstack.newstack.domain.employee.Employee;
import com.examplenewstack.newstack.domain.employee.service.EmployeeCrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@Tag(name = "Funcionário")
public class EmployeeCrudController {

    private final EmployeeCrudService service;

    public EmployeeCrudController(EmployeeCrudService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@RequestBody @Valid EmployeeRequest employeeDTO){

        Employee employee = service.registerEmployee(employeeDTO);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/reports/all")
    public ResponseEntity<?> reports(){
        return ResponseEntity.ok().body(service.findAllEmployees());
    }

    //Função responsável pelo endpoint de mostrar funcionários com paginação
    @GetMapping
    @Operation(summary = "Listar funcionários usando filtros com paginação")
    public ResponseEntity<Page<EmployeeResponse>> getFilteredEmployees(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "5") Integer size,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        return ResponseEntity.ok().body(service.getFilteredEmployees(page, size, orderBy, direction));
    }

    @GetMapping("/reportsBy/{id}")
    public ResponseEntity<?> reportsEmployeeById(@PathVariable int id){
        return ResponseEntity.ok().body(service.getEmployeeProfileById(id));
    }
}
