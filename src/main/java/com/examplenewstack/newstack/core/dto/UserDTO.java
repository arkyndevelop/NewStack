package com.examplenewstack.newstack.core.dto;

import com.examplenewstack.newstack.core.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

// Classe exclusiva para transferência de dados de um formulário para o Data Base
// O mesmo garante que dados sensíveis, como ID, senha, dentre outros não seja exposto para possíveis ataques
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "Nome não pode estar vazio!")
    @Size(min = 3, max = 60)
    private String name;
    @NotBlank(message = "CPF não pode estar vazio!")
    @CPF(message = "CPF inválido!")
    private String CPF;
    @NotBlank(message = "E-mail não pode estar vazio!")
    @Email(message = "E-mail inválido!")
    @Size(min = 10, max = 64)
    private String email;
    @Size(max = 14)
    private String telephone;
    @NotBlank(message = "Senha não pode estar vazia!")
    @Size(min = 8, max = 85)
    private String password;
    @NotBlank(message = "Confirmação de senha não pode estar vazia!")
    private String confirmPassword;


    // Metodo responsável por coletar as informações necessárias diretamente no formulário
    // Após coletar é passado para a devida Entidade (User)
    public User toUser(){
        User user = new User();

        user.setName(this.name);
        user.setCPF(this.CPF);
        user.setEmail(this.email);
        user.setTelephone(this.telephone);
        user.setPassword(this.password);

        return user;
    }
}


