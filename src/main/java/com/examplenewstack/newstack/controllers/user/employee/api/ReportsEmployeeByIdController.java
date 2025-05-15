package com.examplenewstack.newstack.controllers.user.employee.api;

import com.examplenewstack.newstack.dtos.employee.EmployeeDTO;
import com.examplenewstack.newstack.service.user.employee.ReportsEmployeeByIdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class ReportsEmployeeByIdController {

    private final ReportsEmployeeByIdService reportsEmployeeByIdService;


    public ReportsEmployeeByIdController(ReportsEmployeeByIdService reportsEmployeeByIdService) {
        this.reportsEmployeeByIdService = reportsEmployeeByIdService;
    }



    @GetMapping("/reportsBy/{id}")
    public ResponseEntity<?> reportsEmployeeById(@PathVariable Long id){

        return ResponseEntity.ok().body(reportsEmployeeByIdService.reportsEmployeeById(id));


    }
}
