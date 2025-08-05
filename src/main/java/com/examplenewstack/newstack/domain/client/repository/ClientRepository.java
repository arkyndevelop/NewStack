package com.examplenewstack.newstack.domain.client.repository;

import com.examplenewstack.newstack.domain.client.Client;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>, PagingAndSortingRepository<Client, Integer> {

    Client findByCPFAndPassword(String cpf, String password);

    Client findByCPF(String cpf);


    @Modifying
    @Transactional
    @Query("DELETE FROM Client c WHERE c.CPF = :cpf")
    void deleteByCPF(@Param("cpf") String cpf);


    Client findByEmail(String email);

    boolean existsByCPF(String cpf);

    boolean existsByEmail(String email);

    boolean existsByTelephone(String telephone);

    Optional<Client> findByEmailOrCPF(String email, String cpf);

    @Query("SELECT COUNT(c) FROM Client c WHERE EXTRACT(MONTH FROM c.dateRegister) = :month AND EXTRACT(YEAR FROM c.dateRegister) = :actualYear")
    int findAllByMonth(int month, int actualYear);
}
