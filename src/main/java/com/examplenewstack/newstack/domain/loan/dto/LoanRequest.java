package com.examplenewstack.newstack.domain.loan.dto;

public class LoanRequest {

        private int clientId;
        private int bookId;
        private int loanTermDays; // Prazo do empréstimo (30, 60 ou 90 dias)

        // Getters e Setters
        public int getClientId() {
                return clientId;
        }

        public void setClientId(int clientId) {
                this.clientId = clientId;
        }

        public int getBookId() {
                return bookId;
        }

        public void setBookId(int bookId) {
                this.bookId = bookId;
        }

        public int getLoanTermDays() {
                return loanTermDays;
        }

        public void setLoanTermDays(int loanTermDays) {
                this.loanTermDays = loanTermDays;
        }
}