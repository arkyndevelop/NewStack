package com.examplenewstack.newstack.service.user.employee;


import com.examplenewstack.newstack.entity.employee.Employee;
import com.examplenewstack.newstack.exceptions.employee.NoEmployeersFoundByIdException;
import com.examplenewstack.newstack.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class DeleteEmployeeByIdService {

    private final EmployeeRepository employeeRepository;


    public DeleteEmployeeByIdService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public ResponseEntity<Employee> deleteEmployeeById(@RequestParam Long id) {

        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            employeeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        throw new NoEmployeersFoundByIdException();

    }
}
