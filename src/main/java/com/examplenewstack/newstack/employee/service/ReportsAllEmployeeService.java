package com.examplenewstack.newstack.employee.service;


import com.examplenewstack.newstack.employee.Employee;
import com.examplenewstack.newstack.employee.exception.NoEmployeersFoundException;
import com.examplenewstack.newstack.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsAllEmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public ReportsAllEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAllEmployee() {

        List<Employee> employeeList = employeeRepository.findAll();

        if (employeeList.isEmpty()) {
            throw new NoEmployeersFoundException();

        }
        return employeeRepository.findAll();
    }
}
