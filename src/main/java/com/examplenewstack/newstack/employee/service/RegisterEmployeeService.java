package com.examplenewstack.newstack.employee.service;


import com.examplenewstack.newstack.employee.dto.EmployeeRequestDTO;
import com.examplenewstack.newstack.employee.Employee;
import com.examplenewstack.newstack.employee.exception.EmployeersRegisteredDataException;
import com.examplenewstack.newstack.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterEmployeeService {

    private final EmployeeRepository repository;

    public RegisterEmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee registerEmployee(
            EmployeeRequestDTO employeeDTO
    ){
        if(repository.existsByCPF(employeeDTO.CPF())){
            throw new EmployeersRegisteredDataException("cpf");
        }
        if (repository.existsByEmail(employeeDTO.email())) {
            throw new EmployeersRegisteredDataException("email");
        }
        if(repository.existsByTelephone(employeeDTO.telephone())){
            throw new EmployeersRegisteredDataException("telephone");
        }
        return repository.save(employeeDTO.toUser());
    }
}
