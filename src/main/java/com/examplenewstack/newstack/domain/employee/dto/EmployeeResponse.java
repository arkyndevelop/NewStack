package com.examplenewstack.newstack.domain.employee.dto;

import com.examplenewstack.newstack.core.dto.UserResponseDTO;
import com.examplenewstack.newstack.domain.employee.Employee;
import com.examplenewstack.newstack.domain.employee.enums.TypeEmployee;

public record EmployeeResponse(
        Integer id,
        String name,
        String CPF,
        String email,
        String telephone,
        TypeEmployee typeEmployee
) implements UserResponseDTO {

    public static EmployeeResponse fromEntity(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getCPF(),
                employee.getEmail(),
                employee.getTelephone(),
                employee.getTypeEmployee()
        );
    }
}
