package com.examplenewstack.newstack.domain.admin.dto;

import com.examplenewstack.newstack.domain.admin.AdminMaster;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record AdminRequest(
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
        String password
) {

    public AdminMaster toAdm() {
        AdminMaster adminMaster = new AdminMaster();
        adminMaster.setName(name);
        adminMaster.setCPF(CPF);
        adminMaster.setEmail(email);
        adminMaster.setTelephone(telephone);
        adminMaster.setPassword(password);

        return adminMaster;
    }
}
