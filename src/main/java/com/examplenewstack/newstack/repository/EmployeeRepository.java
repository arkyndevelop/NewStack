package com.examplenewstack.newstack.repository;

import com.examplenewstack.newstack.model.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByCPF(String cpf);

    void deleteByCPF(String cpf);
}
