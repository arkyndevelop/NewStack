package com.examplenewstack.newstack.repository;

import com.examplenewstack.newstack.model.usersinfo.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByCPFAndPassword(String cpf, String password);
}
