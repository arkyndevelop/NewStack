package com.examplenewstack.newstack.domain.employee.service;


import com.examplenewstack.newstack.domain.employee.Employee;
import com.examplenewstack.newstack.domain.employee.dto.EmployeeResponseDTO;
import com.examplenewstack.newstack.domain.employee.exception.NoEmployeersFoundException;
import com.examplenewstack.newstack.domain.employee.repository.EmployeeRepository;
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
                        employee.getTelephone()))
                .toList();
    }
}
