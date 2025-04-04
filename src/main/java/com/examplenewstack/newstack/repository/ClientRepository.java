package com.examplenewstack.newstack.repository;

import com.examplenewstack.newstack.model.usersinfo.client.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository <Client, Long> {
}
