package com.examplenewstack.newstack.domain.employee.service;


import com.examplenewstack.newstack.domain.employee.Employee;
import com.examplenewstack.newstack.domain.employee.dto.EmployeeResponse;
import com.examplenewstack.newstack.domain.employee.exception.NoEmployeersFoundByIdException;
import com.examplenewstack.newstack.domain.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportsEmployeeByIdService {

    private final EmployeeRepository repository;

    public ReportsEmployeeByIdService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public EmployeeResponse reportsEmployeeById(
            int id
    ) {
        Optional<Employee> employee = repository.findById(id);
        if (employee.isEmpty()) {
            throw new NoEmployeersFoundByIdException();
        }
        return new EmployeeResponse(
                employee.get().getId(),
                employee.get().getName(),
                employee.get().getCPF(),
                employee.get().getEmail(),
                employee.get().getTelephone(),
                employee.get().getTypeEmployee()
        );
    }
}
