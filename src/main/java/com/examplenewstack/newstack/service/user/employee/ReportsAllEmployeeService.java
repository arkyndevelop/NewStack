package com.examplenewstack.newstack.service.user.employee;


import com.examplenewstack.newstack.entity.employee.Employee;
import com.examplenewstack.newstack.exception.ClientsException.CustomException;
import com.examplenewstack.newstack.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsAllEmployeeService {

    private final EmployeeRepository employeeRepository;

    public ReportsAllEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAllEmployee(){

        List<Employee> employeeList = employeeRepository.findAll();

        if(employeeList.isEmpty()){
            throw new CustomException("Erro: Nenhum empregado encontrado!");

        }
        return employeeRepository.findAll();
    }
}
