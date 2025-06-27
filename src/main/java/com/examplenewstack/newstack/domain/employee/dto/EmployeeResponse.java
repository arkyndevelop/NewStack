package com.examplenewstack.newstack.domain.employee.dto;

import com.examplenewstack.newstack.core.dto.UserResponseDTO;
import com.examplenewstack.newstack.domain.employee.Employee;

public record EmployeeResponse(
        Integer id,
        String name,
        String CPF,
        String email,
        String telephone
) implements UserResponseDTO {

    public static EmployeeResponse fromEntity(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getCPF(),
                employee.getEmail(),
                employee.getTelephone()
        );
    }
}
