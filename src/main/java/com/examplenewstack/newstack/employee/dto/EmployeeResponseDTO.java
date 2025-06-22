package com.examplenewstack.newstack.employee.dto;

import com.examplenewstack.newstack.core.dto.UserResponseDTO;
import com.examplenewstack.newstack.employee.Employee;

public record EmployeeResponseDTO(
        Integer id,
        String name,
        String CPF,
        String email,
        String telephone
) implements UserResponseDTO {

    public static EmployeeResponseDTO fromEntity(Employee employee) {
        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getName(),
                employee.getCPF(),
                employee.getEmail(),
                employee.getTelephone()
        );
    }
}
