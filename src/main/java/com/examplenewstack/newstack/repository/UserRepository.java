package com.examplenewstack.newstack.repository;

import com.examplenewstack.newstack.model.usersinfo.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    com.examplenewstack.newstack.model.usersinfo.User findByCPFAndPassword(String cpf, String password);
}
