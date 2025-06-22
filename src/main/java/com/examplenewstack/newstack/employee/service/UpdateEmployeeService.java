package com.examplenewstack.newstack.employee.service;

import com.examplenewstack.newstack.employee.dto.EmployeeRequestDTO;
import com.examplenewstack.newstack.employee.Employee;
import com.examplenewstack.newstack.employee.exception.NoEmployeersFoundByIdException;
import com.examplenewstack.newstack.employee.exception.EmployeersSamePasswordException;
import com.examplenewstack.newstack.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UpdateEmployeeService {

    private final EmployeeRepository repository;

    public UpdateEmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Employee> updateEmployee(
            EmployeeRequestDTO request,
            Integer id
    ){
        Optional<Employee> employeeExisting = repository.findById(id);
        if (employeeExisting.isEmpty()) {
            throw new NoEmployeersFoundByIdException();
        }
        Employee employee = repository.getReferenceById(id);

        employee.setName(request.name());
        employee.setCPF(request.CPF());
        employee.setEmail(request.email());
        employee.setTelephone(request.telephone());

        if (!Objects.equals(request.password(), request.confirmPassword())) {
            throw new EmployeersSamePasswordException();
        }
        employee.setPassword(request.password());

        Employee updateEmployee = repository.save(employee);
        return ResponseEntity.ok(updateEmployee);
    }
}
