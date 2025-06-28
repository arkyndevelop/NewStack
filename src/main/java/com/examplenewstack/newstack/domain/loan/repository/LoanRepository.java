package com.examplenewstack.newstack.domain.loan.repository;

import com.examplenewstack.newstack.domain.loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  LoanRepository extends JpaRepository<Loan, Integer> {





}
