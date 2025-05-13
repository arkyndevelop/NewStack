package com.examplenewstack.newstack.service.user.employee;

import com.examplenewstack.newstack.entity.dto.employeedto.EmployeeDTO;
import com.examplenewstack.newstack.entity.employee.Employee;
import com.examplenewstack.newstack.exception.CustomException;
import com.examplenewstack.newstack.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class UpdateEmployeeService {

    private final EmployeeRepository employeeRepository;

    public UpdateEmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<Employee> updateEmployee(
            @RequestBody EmployeeDTO employeeDTO,
            @RequestParam Long id
            ){
        Optional<Employee> employeeExisting = employeeRepository.findById(id);
        if(employeeExisting.isPresent()){
            Employee employee = employeeRepository.getReferenceById(id);

            employee.setName(employeeDTO.getName());
            employee.setCPF(employeeDTO.getCPF());
            employee.setEmail(employeeDTO.getEmail());
            employee.setTelephone(employeeDTO.getTelephone());
            employee.setPassword(employeeDTO.getPassword());

            Employee updateEmployee = employeeRepository.save(employee);
            return ResponseEntity.ok(updateEmployee);
        }
        throw new CustomException("Erro: Funcionário não localizado!");
    }
}
