package com.examplenewstack.newstack.repository;

import com.examplenewstack.newstack.model.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {




    User findByCPFAndPassword(String cpf, String password);
}
