package com.examplenewstack.newstack.domain.employee.repository;

import com.examplenewstack.newstack.domain.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByCPF(String cpf);

    boolean existsByCPF(String cpf);

    boolean existsByEmail(String email);

    boolean existsByTelephone(String telephone);
}
