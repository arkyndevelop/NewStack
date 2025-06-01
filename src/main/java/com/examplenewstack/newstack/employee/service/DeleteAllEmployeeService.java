package com.examplenewstack.newstack.employee.service;


import com.examplenewstack.newstack.employee.Employee;
import com.examplenewstack.newstack.employee.exception.NoEmployeersFoundException;
import com.examplenewstack.newstack.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeleteAllEmployeeService {

    private final EmployeeRepository repository;


    public DeleteAllEmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void deleteAllEmployee(){
        List<Employee> employeeList =  this.repository.findAll();
        if(employeeList.isEmpty()){
            throw new NoEmployeersFoundException();
        }
        repository.deleteAll();
    }
}
