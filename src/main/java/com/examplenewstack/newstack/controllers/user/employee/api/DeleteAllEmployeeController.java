package com.examplenewstack.newstack.controllers.user.employee.api;


import com.examplenewstack.newstack.service.user.employee.DeleteAllEmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class DeleteAllEmployeeController {

    private final DeleteAllEmployeeService deleteAllEmployeeService;

    public DeleteAllEmployeeController(DeleteAllEmployeeService deleteAllEmployeeService) {
        this.deleteAllEmployeeService = deleteAllEmployeeService;
    }

    @DeleteMapping("/delete/all")

    public ResponseEntity<?> deleteAllEmployees() {

        deleteAllEmployeeService.deleteAllEmployee();

        return ResponseEntity.ok().build();

    }


}
