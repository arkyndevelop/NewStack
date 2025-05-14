package com.examplenewstack.newstack.repository;

import com.examplenewstack.newstack.entity.loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  LoanRepository extends JpaRepository<Loan, Long> {





}
