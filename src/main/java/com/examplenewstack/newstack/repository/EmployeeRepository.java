package com.examplenewstack.newstack.repository;

import com.examplenewstack.newstack.entity.employee.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByCPF(String cpf);

    boolean existsByCPF(String cpf);

    boolean existsByEmail(String email);

    boolean existsByTelephone(String telephone);
}
