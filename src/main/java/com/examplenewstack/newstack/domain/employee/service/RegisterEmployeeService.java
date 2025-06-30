package com.examplenewstack.newstack.domain.employee.service;

import com.examplenewstack.newstack.domain.employee.Employee;
import com.examplenewstack.newstack.domain.employee.dto.EmployeeRequest;
import com.examplenewstack.newstack.domain.employee.exception.EmployeersRegisteredDataException;
import com.examplenewstack.newstack.domain.employee.repository.EmployeeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterEmployeeService {

    private final EmployeeRepository repository;
    private final PasswordEncoder passwordEncoder;

    public RegisterEmployeeService(EmployeeRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Employee registerEmployee(
            EmployeeRequest employeeDTO
    ) {
        if(repository.existsByCPF(employeeDTO.CPF())){
            throw new EmployeersRegisteredDataException("cpf");
        }

        if(repository.existsByCPF(employeeDTO.CPF())){
            throw new EmployeersRegisteredDataException("cpf");
        }
        if (repository.existsByEmail(employeeDTO.email())) {
            throw new EmployeersRegisteredDataException("email");
        }
        if(repository.existsByTelephone(employeeDTO.telephone())){
            throw new EmployeersRegisteredDataException("telephone");
        }

        Employee newEmployee = employeeDTO.toEmployee();

        String encodedPassword = passwordEncoder.encode(employeeDTO.password());
        newEmployee.setPassword(encodedPassword);

        return repository.save(newEmployee);
    }
}