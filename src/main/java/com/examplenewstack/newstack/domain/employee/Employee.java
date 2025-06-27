package com.examplenewstack.newstack.domain.employee;

import com.examplenewstack.newstack.core.entity.User;
import com.examplenewstack.newstack.domain.book.Book;
import com.examplenewstack.newstack.domain.employee.enums.TypeEmployee;
import com.examplenewstack.newstack.domain.loan.Loan;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee extends User {

    private TypeEmployee typeEmployee;

    //Relacionamento com book
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Book> books;

    @Override
    public User toUser() {
        return super.toUser();
    }
}
