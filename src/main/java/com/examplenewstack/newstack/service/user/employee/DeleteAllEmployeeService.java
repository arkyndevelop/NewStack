package com.examplenewstack.newstack.service.user.employee;


import com.examplenewstack.newstack.entity.employee.Employee;
import com.examplenewstack.newstack.exceptions.employee.NoEmployeersFoundException;
import com.examplenewstack.newstack.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteAllEmployeeService {

    private final EmployeeRepository employeeRepository;


    public DeleteAllEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public void deleteAllEmployee(){

        List<Employee> employeeList =  this.employeeRepository.findAll();

        if(employeeList.isEmpty()){

            throw new NoEmployeersFoundException();

        }

        employeeRepository.deleteAll();

    }
}
