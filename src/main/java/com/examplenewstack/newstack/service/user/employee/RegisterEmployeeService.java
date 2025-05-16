package com.examplenewstack.newstack.service.user.employee;


import com.examplenewstack.newstack.dtos.employee.EmployeeDTO;
import com.examplenewstack.newstack.entity.employee.Employee;
import com.examplenewstack.newstack.exceptions.employee.EmployeersRegisteredDataException;
import com.examplenewstack.newstack.repository.EmployeeRepository;
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
