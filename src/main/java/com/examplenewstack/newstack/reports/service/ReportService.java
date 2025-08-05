package com.examplenewstack.newstack.reports.service;

import com.examplenewstack.newstack.domain.book.repository.BookRepository;
import com.examplenewstack.newstack.domain.client.repository.ClientRepository;
import com.examplenewstack.newstack.domain.loan.dto.LoanResponse;
import com.examplenewstack.newstack.domain.loan.repository.LoanRepository;
import com.examplenewstack.newstack.domain.loan.service.LoanCrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final LoanRepository loanRepository;
    private final ClientRepository clientRepository;
    private final BookRepository bookRepository;

    private final LoanCrudService loanCrudService;

    public ReportService(LoanRepository loanRepository, ClientRepository clientRepository, BookRepository bookRepository, LoanCrudService loanCrudService) {
        this.loanRepository = loanRepository;
        this.clientRepository = clientRepository;
        this.bookRepository = bookRepository;
        this.loanCrudService = loanCrudService;
    }

    // Service Report para Loan (inicio)
    @Transactional(readOnly = true)
    public Map<String, Double> reportByLoanAmount() {
        int total = loanCrudService.reportAll().size();

        Map<String, Long> count = loanCrudService.reportAll().stream()
                .collect(Collectors.groupingBy(LoanResponse::bookTitle, Collectors.counting()));

        Map<String, Long> topTen = count.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        Map<String, Double> percent = new LinkedHashMap<>();
        topTen.forEach((book, quantity) -> {
            double perc = (quantity * 100.0) / total;
            percent.put(book, Math.round(perc * 10.0) / 10.0);
        });

        // System.out.println("Os percentuais são: " + percent);
        return percent;
    }

    @Transactional(readOnly = true)
    public Map<String, Integer> reportByLoanMonth() {
        Map<String, Integer> dataLoanForMonth = new LinkedHashMap<>();

        LocalDate today = LocalDate.now();
        int actualMonth = today.getMonthValue();
        int actualYear = today.getYear();

        for (int month = 1; month <= actualMonth; month++) {
            Month nameMonth = Month.of(month);
            String name = nameMonth.toString().substring(0, 3); // Ex.: JAN, FEB...

            int quantityLoansForMonth = loanRepository.findAllByMonth(month, actualYear);
            dataLoanForMonth.put(name, quantityLoansForMonth);
        }

        return dataLoanForMonth;
    }
    // Service Report para Loan (fim)


    // Service Report para Client (inicio)
    @Transactional(readOnly = true)
    public Map<String, Integer> reportRegisterClientByMonth() {
        Map<String, Integer> dataClientForMonth = new LinkedHashMap<>();

        LocalDate today = LocalDate.now();
        int actualMonth = today.getMonthValue();
        int actualYear = today.getYear();

        for (int month = 1; month <= actualMonth; month++) {
            Month nameMonth = Month.of(month);
            String name = nameMonth.toString().substring(0, 3); // Ex.: JAN, FEB...

            int quantityRegisterClientForMonth = clientRepository.findAllByMonth(month, actualYear);
            dataClientForMonth.put(name, quantityRegisterClientForMonth);
        }

        return dataClientForMonth;
    }
    // Service Report para Client (fim)
}
