package com.examplenewstack.newstack.domain.admin.repository;

import com.examplenewstack.newstack.domain.admin.AdminMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminMaster, Long> {
    AdminMaster findByCPFAndPassword(String cpf, String password);

    AdminMaster findByCPF(String cleanIdentifier);
}
