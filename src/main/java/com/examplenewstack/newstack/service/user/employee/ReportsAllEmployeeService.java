package com.examplenewstack.newstack.service.user.employee;


import com.examplenewstack.newstack.entity.employee.Employee;
import com.examplenewstack.newstack.exceptions.employee.NoEmployeersFoundException;
import com.examplenewstack.newstack.repository.EmployeeRepository;
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
