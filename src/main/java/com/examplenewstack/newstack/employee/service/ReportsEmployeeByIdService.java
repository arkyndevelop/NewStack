package com.examplenewstack.newstack.employee.service;


import com.examplenewstack.newstack.employee.Employee;
import com.examplenewstack.newstack.employee.dto.EmployeeResponseDTO;
import com.examplenewstack.newstack.employee.exception.NoEmployeersFoundByIdException;
import com.examplenewstack.newstack.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportsEmployeeByIdService {

    private final EmployeeRepository repository;

    public ReportsEmployeeByIdService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public EmployeeResponseDTO reportsEmployeeById(
            int id
    ) {
        Optional<Employee> employee = repository.findById(id);
        if (employee.isEmpty()) {
            throw new NoEmployeersFoundByIdException();
        }
        return new EmployeeResponseDTO(
                employee.get().getId(),
                employee.get().getName(),
                employee.get().getCPF(),
                employee.get().getEmail(),
                employee.get().getTelephone(),
                employee.get().getTypeEmployee()
        );
    }
}
