package com.examplenewstack.newstack.entity.employee;

import com.examplenewstack.newstack.entity.User;
import com.examplenewstack.newstack.entity.book.Book;
import com.examplenewstack.newstack.enums.TypeEmployee;
import com.examplenewstack.newstack.entity.loan.Loan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "librarian")
public class Employee extends User {
    @Getter
    @Setter
    private TypeEmployee typeEmployee;
    public Employee() { super(); }

    //Relacionamento com Loan
    @OneToMany(mappedBy = "employee",fetch = FetchType.LAZY)
    private List<Loan> loans;

    //Relacionamento com book
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Book> books;


    public Employee(String name, String CPF, String email, String telephone, String password) {
        super(name, CPF, email, telephone, password);
    }

    @Override
    public User toUser() {
        return super.toUser();
    }
}
