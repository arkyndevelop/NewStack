package com.examplenewstack.newstack.domain.employee.dto;

import com.examplenewstack.newstack.domain.employee.Employee;
import com.examplenewstack.newstack.domain.employee.enums.TypeEmployee;

public record EmployeeResponse(
        int id,
        String name,
        String CPF,
        String email,
        TypeEmployee typeEmployee
) {
    public static EmployeeResponse fromEntity(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getCPF(),
                employee.getEmail(),
                employee.getTypeEmployee()
        );
    }
}