package com.examplenewstack.newstack.employee.service;


import com.examplenewstack.newstack.employee.Employee;
import com.examplenewstack.newstack.employee.exception.NoEmployeersFoundException;
import com.examplenewstack.newstack.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteAllEmployeeService {

    @Autowired
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
