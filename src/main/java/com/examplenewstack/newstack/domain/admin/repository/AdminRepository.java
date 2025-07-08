package com.examplenewstack.newstack.domain.admin.repository;

import com.examplenewstack.newstack.domain.admin.AdminMaster;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminMaster, Integer> {
    AdminMaster findByCPFAndPassword(String cpf, String password);

    AdminMaster findByCPF(String cleanIdentifier);

    boolean existsByCPF(@NotBlank(message = "CPF não pode estar vazio!") @CPF(message = "CPF inválido!") String cpf);

    boolean existsByEmail(@NotBlank(message = "E-mail não pode estar vazio!") @Email(message = "E-mail inválido!") @Size(min = 10, max = 64) String email);

    boolean existsByTelephone(@Size(max = 14) String telephone);
}
