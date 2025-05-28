package com.examplenewstack.newstack.employee.service;


import com.examplenewstack.newstack.employee.dto.EmployeeDTO;
import com.examplenewstack.newstack.employee.Employee;
import com.examplenewstack.newstack.employee.exception.EmployeersRegisteredDataException;
import com.examplenewstack.newstack.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RegisterEmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;


    public RegisterEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee registerEmployee(
            @RequestBody EmployeeDTO employeeDTO){

        if(employeeRepository.existsByCPF(employeeDTO.getCPF())){
            throw new EmployeersRegisteredDataException("cpf");
        }

        if (employeeRepository.existsByEmail(employeeDTO.getEmail())) {
            throw new EmployeersRegisteredDataException("email");
        }

        if(employeeRepository.existsByTelephone(employeeDTO.getTelephone())){
            throw new EmployeersRegisteredDataException("telephone");
        }

        return employeeRepository.save(employeeDTO.toUser());
    }
}
