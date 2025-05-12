package com.examplenewstack.newstack.controller.user.employeeController.service;

import com.examplenewstack.newstack.entity.employee.Employee;
import com.examplenewstack.newstack.entity.usersinfo.client.Client;
import com.examplenewstack.newstack.repository.EmployeeRepository;

import java.util.Optional;

public class UpdateEmployee {
    private final EmployeeRepository employeeRepository;

    public UpdateEmployee(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void updateClient(String cpf, Client clientUpdate){
        Optional<Employee> employeeOptional = Optional.ofNullable(employeeRepository.findByCPF(cpf));

        if(employeeOptional.isPresent()){
            Employee employeeExist = employeeOptional.get();

            if(employeeExist.getName() != null){
                employeeExist.setName(employeeExist.getName());
            }
            if(employeeExist.getCPF() != null){
                employeeExist.setCPF(employeeExist.getCPF());
            }
            if(employeeExist.getEmail() != null){
                employeeExist.setEmail(employeeExist.getEmail());
            }
            if(employeeExist.getTelephone() != null){
                employeeExist.setTelephone(employeeExist.getTelephone());
            }
            if(employeeExist.getPassword() != null){
                employeeExist.setPassword(employeeExist.getPassword());
            }
            employeeRepository.save(employeeExist);
        } else {
            throw new RuntimeException("Cliente não encontrado pelo CPF " + cpf);
        }
    }
}
