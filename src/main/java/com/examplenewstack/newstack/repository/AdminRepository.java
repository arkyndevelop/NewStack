package com.examplenewstack.newstack.repository;

import com.examplenewstack.newstack.model.usersinfo.adminMaster.AdminMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminMaster, Long> {
    AdminMaster findByCPFAndPassword(String cpf, String password);
}
