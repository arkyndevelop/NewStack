package com.examplenewstack.newstack.service.user.employee;


import com.examplenewstack.newstack.entity.dto.employeedto.EmployeeDTO;
import com.examplenewstack.newstack.entity.employee.Employee;
import com.examplenewstack.newstack.exception.EmployeeExceptions.EmployeeRegisteredDataException;
import com.examplenewstack.newstack.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RegisterEmployeeService {

private final EmployeeRepository employeeRepository;


    public RegisterEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee registerEmployee(
            @RequestBody EmployeeDTO employeeDTO){

        if(employeeRepository.existsByCPF(employeeDTO.getCPF())){
            throw new EmployeeRegisteredDataException("cpf");
        }

        if (employeeRepository.existsByEmail(employeeDTO.getEmail())) {
            throw new EmployeeRegisteredDataException("email");
        }

        if(employeeRepository.existsByTelephone(employeeDTO.getTelephone())){
            throw new EmployeeRegisteredDataException("telephone");
        }

        return employeeRepository.save(employeeDTO.toUser());
    }
}
