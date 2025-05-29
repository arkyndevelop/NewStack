package com.examplenewstack.newstack.employee.service;

import com.examplenewstack.newstack.employee.dto.EmployeeRequestDTO;
import com.examplenewstack.newstack.employee.Employee;
import com.examplenewstack.newstack.employee.exception.NoEmployeersFoundByIdException;
import com.examplenewstack.newstack.employee.exception.EmployeersSamePasswordException;
import com.examplenewstack.newstack.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class UpdateEmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public UpdateEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<Employee> updateEmployee(@RequestBody EmployeeRequestDTO employeeDTO, @RequestParam Long id) {
        Optional<Employee> employeeExisting = employeeRepository.findById(id);
        if (employeeExisting.isEmpty()) {
            throw new NoEmployeersFoundByIdException();
        }
        Employee employee = employeeRepository.getReferenceById(id);

        employee.setName(employeeDTO.name());
        employee.setCPF(employeeDTO.CPF());
        employee.setEmail(employeeDTO.email());
        employee.setTelephone(employeeDTO.telephone());
        if (employeeDTO.password().equals(employeeDTO.confirmPassword())) {
            throw new EmployeersSamePasswordException();
        }
        employee.setPassword(employeeDTO.password());

        Employee updateEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updateEmployee);
    }
}


