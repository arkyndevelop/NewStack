package com.examplenewstack.newstack.employee.service;


import com.examplenewstack.newstack.employee.Employee;
import com.examplenewstack.newstack.employee.exception.NoEmployeersFoundByIdException;
import com.examplenewstack.newstack.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class ReportsEmployeeByIdService {

    @Autowired
    private final EmployeeRepository employeeRepository;


    public ReportsEmployeeByIdService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Optional<Employee> reportsEmployeeById(@PathVariable Long id) {

        Optional<Employee> employeeList = employeeRepository.findById(id);

        if (employeeList.isEmpty()) {

            throw new NoEmployeersFoundByIdException();

        }
        return employeeRepository.findById(id);

    }

}
