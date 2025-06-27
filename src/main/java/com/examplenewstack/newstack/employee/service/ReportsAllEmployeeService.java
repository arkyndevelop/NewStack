package com.examplenewstack.newstack.employee.service;


import com.examplenewstack.newstack.employee.Employee;
import com.examplenewstack.newstack.employee.dto.EmployeeResponseDTO;
import com.examplenewstack.newstack.employee.exception.NoEmployeersFoundException;
import com.examplenewstack.newstack.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsAllEmployeeService {

    private final EmployeeRepository repository;

    public ReportsAllEmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<EmployeeResponseDTO> findAllEmployee() {
        List<Employee> employeeList = repository.findAll();
        if (employeeList.isEmpty()) {
            throw new NoEmployeersFoundException();
        }

        return employeeList
                .stream()
                .map(employee -> new EmployeeResponseDTO(
                        employee.getId(),
                        employee.getName(),
                        employee.getCPF(),
                        employee.getEmail(),
                        employee.getTelephone(),
                        employee.getTypeEmployee()))
                .toList();
    }
}
