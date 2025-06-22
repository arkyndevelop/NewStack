package com.examplenewstack.newstack.client.dto;

import com.examplenewstack.newstack.address.dto.AddressRequest;
import com.examplenewstack.newstack.core.dto.UserRequestDTO;
import com.examplenewstack.newstack.client.Client;
import com.examplenewstack.newstack.loan.Loan;
import com.examplenewstack.newstack.loan.dto.LoanRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public record ClientRequestDTO(
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

//      AddressRequest address,
//      LoanRequest loan

) implements UserRequestDTO {

    public Client toClient() {
        Client client = new Client();
        client.setName(name);
        client.setCPF(CPF);
        client.setEmail(email);
        client.setTelephone(telephone);
        client.setPassword(password);

//        client.setAddress((address != null) ? address.toAddress() : null);
//        client.setLoans((loan() == null) ? (List<Loan>) loan().toLoan() : null);

        return client;
    }
}
