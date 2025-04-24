package com.examplenewstack.newstack.repository;

import com.examplenewstack.newstack.model.employee.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByCPF(String cpf);

    boolean existsByCPF(String cpf);

    @Modifying
    @Transactional
    @Query("DELETE FROM Employee e WHERE e.CPF = :cpf")
    void deleteByCPF(@Param("cpf") String cpf);
}
