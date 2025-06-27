package com.examplenewstack.newstack.client.repository;

import com.examplenewstack.newstack.client.Client;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByCPFAndPassword(String cpf, String password);

    boolean findByCPF(String cpf);


    @Modifying
    @Transactional
    @Query("DELETE FROM Client c WHERE c.CPF = :cpf")
    void deleteByCPF(@Param("cpf") String cpf);


    boolean findByEmail(String email);

    boolean existsByCPF(String cpf);

    boolean existsByEmail(String email);

    boolean existsByTelephone(String telephone);
}
