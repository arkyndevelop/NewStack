package com.examplenewstack.newstack.employee.dto;

import com.examplenewstack.newstack.core.dto.UserRequestDTO;
import com.examplenewstack.newstack.employee.Employee;
import com.examplenewstack.newstack.employee.enums.TypeEmployee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record EmployeeRequestDTO(
        @NotBlank(message = "Nome não pode estar vazio!")
        @Size(min = 3, max = 60)
        String name,

        @NotBlank(message = "CPF não pode estar vazio!")
        @CPF(message = "CPF inválido!")
        String CPF,

        @NotBlank(message = "E-mail não pode estar vazio!")
        @Email(message = "E-mail inválido!")
        @Size(min = 10, max = 64)
        String email,

        @Size(max = 14)
        String telephone,

        @NotBlank(message = "Senha não pode estar vazia!")
        @Size(min = 8, max = 85)
        String password,

        @NotBlank(message = "Confirmação de senha não pode estar vazia!")
        String confirmPassword,

        @NotNull(message = "O tipo de funcionário não pode ser nulo!")
        TypeEmployee typeEmployee
) implements UserRequestDTO {

    public Employee toUser() {
        Employee employee = new Employee();

        employee.setName(name);
        employee.setCPF(CPF);
        employee.setEmail(email);
        employee.setTelephone(telephone);
        employee.setPassword(password);
        employee.setTypeEmployee(typeEmployee);

        return employee;
    }
}
