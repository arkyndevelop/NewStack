package com.examplenewstack.newstack.controllers.user.employee.api;

import com.examplenewstack.newstack.entity.employee.Employee;
import com.examplenewstack.newstack.repository.EmployeeRepository;
import com.examplenewstack.newstack.service.user.employee.ReportsAllEmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;
@RestController
@RequestMapping("/employee")
public class ReportsAllEmployeeController {
   private final ReportsAllEmployeeService reportsAllEmployeeService;

    public ReportsAllEmployeeController(ReportsAllEmployeeService reportsAllEmployeeService) {
        this.reportsAllEmployeeService = reportsAllEmployeeService;
    }

    @GetMapping("/reports/all")
    public ResponseEntity<?> reports(){

       return ResponseEntity.ok().body(reportsAllEmployeeService.findAllEmployee());


    }
}
