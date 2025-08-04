package com.examplenewstack.newstack.domain.loan.repository;

import com.examplenewstack.newstack.domain.book.Book;
import com.examplenewstack.newstack.domain.loan.Loan;
import com.examplenewstack.newstack.domain.loan.enums.StatusLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  LoanRepository extends JpaRepository<Loan, Integer> {
    List<Loan> findByBookId(Integer bookId);

    List<Loan> findByClientId(Integer clientId);

    int countByBookAndActualReturnDateIsNull(Book book);

    @Query("SELECT l FROM Loan l WHERE l.client.id = :clientId AND l.actualReturnDate IS NULL")
    List<Loan> findActiveLoansByClientId(@Param("clientId") Integer clientId);

    @Query("SELECT l FROM Loan l LEFT JOIN FETCH l.book WHERE l.client.id = :clientId")
    List<Loan> findByClientIdWithBook(@Param("clientId") Integer clientId);

    @Query("SELECT COUNT(l) FROM Loan l WHERE EXTRACT(MONTH FROM l.loanDate) = :month AND EXTRACT(YEAR FROM l.loanDate) = :year")
    int findAllByMonth(@Param("month") Integer month, @Param("year") Integer year);

    List<Loan> findByBookAndStatusOrderByLoanDateAsc(Book book, StatusLoan status);
}
