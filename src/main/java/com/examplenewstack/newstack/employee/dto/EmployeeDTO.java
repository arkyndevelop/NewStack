package com.examplenewstack.newstack.employee.dto;

import com.examplenewstack.newstack.core.dto.UserDTO;
import com.examplenewstack.newstack.employee.Employee;
import com.examplenewstack.newstack.employee.enums.TypeEmployee;

public class EmployeeDTO extends UserDTO {
    private TypeEmployee typeEmployee;

    public EmployeeDTO() { super(); }

    public EmployeeDTO(String name, String CPF, String email, String telephone, String password, String confirmPassword, TypeEmployee typeEmployee) {
        super(name, CPF, email, telephone, password, confirmPassword);
        this.typeEmployee = typeEmployee;
    }

    public TypeEmployee getTypeEmployee() {
        return typeEmployee;
    }

    public void setTypeEmployee(TypeEmployee typeEmployee) {
        this.typeEmployee = typeEmployee;
    }

    // Metodo responsável por adicionar valores aos atributos de uma classe
    @Override
    public Employee toUser() {
        Employee employee = new Employee();

        employee.setName(this.getName());
        employee.setCPF(this.getCPF());
        employee.setEmail(this.getEmail());
        employee.setTelephone(this.getTelephone());
        employee.setPassword(this.getPassword());
        employee.setTypeEmployee(this.getTypeEmployee());

        return employee;
    }
}
