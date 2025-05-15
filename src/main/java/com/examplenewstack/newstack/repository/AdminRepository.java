package com.examplenewstack.newstack.repository;

import com.examplenewstack.newstack.entity.user.admin.AdminMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminMaster, Long> {
    AdminMaster findByCPFAndPassword(String cpf, String password);
}
