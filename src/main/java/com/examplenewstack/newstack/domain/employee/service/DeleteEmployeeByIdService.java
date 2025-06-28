package com.examplenewstack.newstack.domain.employee.service;


import com.examplenewstack.newstack.domain.employee.Employee;
import com.examplenewstack.newstack.domain.employee.exception.NoEmployeersFoundByIdException;
import com.examplenewstack.newstack.domain.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class DeleteEmployeeByIdService {

    private final EmployeeRepository repository;

    public DeleteEmployeeByIdService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Employee> deleteEmployeeById(
            int id
    ){
        Optional<Employee> employee = repository.findById(id);
        if (employee.isEmpty()) {
            throw new NoEmployeersFoundByIdException();
        }

        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
