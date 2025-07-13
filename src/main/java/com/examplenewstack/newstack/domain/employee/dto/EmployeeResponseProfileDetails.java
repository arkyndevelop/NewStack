package com.examplenewstack.newstack.domain.employee.dto;

import com.examplenewstack.newstack.domain.employee.Employee;
import com.examplenewstack.newstack.domain.employee.enums.TypeEmployee;

import java.time.Instant;

public record EmployeeResponseProfileDetails(
        int id,
        String name,
        String CPF,
        String email,
        String telephone,
        TypeEmployee typeEmployee,
        Instant dateRegister
) {
    public static EmployeeResponseProfileDetails fromEntity(Employee employee) {
        return new EmployeeResponseProfileDetails(
                employee.getId(),
                employee.getName(),
                employee.getCPF(),
                employee.getEmail(),
                employee.getTelephone(),
                employee.getTypeEmployee(),
                employee.getDateRegister()
        );
    }
}