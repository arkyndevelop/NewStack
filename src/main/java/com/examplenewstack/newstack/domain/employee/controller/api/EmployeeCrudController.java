package com.examplenewstack.newstack.domain.employee.controller.api;

import com.examplenewstack.newstack.domain.employee.dto.EmployeeRequest;
import com.examplenewstack.newstack.domain.employee.Employee;
import com.examplenewstack.newstack.domain.employee.service.EmployeeCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
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
        return ResponseEntity.ok().body(service.reportsAllEmployee());
    }

    @GetMapping("/reportsBy/{id}")
    public ResponseEntity<?> reportsEmployeeById(@PathVariable int id){
        return ResponseEntity.ok().body(service.reportsEmployeeById(id));
    }

    @DeleteMapping("/update/{id}")
    public ResponseEntity<?> updateEmployee(@RequestBody @Valid EmployeeRequest request, @PathVariable int id) {

        service.updateEmployee(request, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<?> deleteAllEmployees() {

        service.deleteAllEmployee();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<?> deleteByCPF(@PathVariable int id){
        service.deleteEmployeeById(id);
        return ResponseEntity.ok().build();
    }
}
