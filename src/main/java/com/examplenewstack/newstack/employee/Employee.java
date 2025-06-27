package com.examplenewstack.newstack.employee;

import com.examplenewstack.newstack.core.entity.User;
import com.examplenewstack.newstack.book.Book;
import com.examplenewstack.newstack.loan.Loan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee extends User {

    @Enumerated(EnumType.STRING)
    private TypeEmployee typeEmployee;

    public Employee() { super(); }

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
