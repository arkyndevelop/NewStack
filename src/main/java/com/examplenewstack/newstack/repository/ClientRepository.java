package com.examplenewstack.newstack.repository;

import com.examplenewstack.newstack.model.usersinfo.client.Client;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByCPFAndPassword(String cpf, String password);

    Client findByCPF(String cpf);


    @Modifying
    @Transactional
    @Query("DELETE FROM Client c WHERE c.CPF = :cpf")
    void deleteByCPF(@Param("cpf") String cpf);


    String CPF(String cpf);
}
