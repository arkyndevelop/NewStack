package com.examplenewstack.newstack.entity.employee;

import com.examplenewstack.newstack.entity.User;
import com.examplenewstack.newstack.entity.enums.TypeEmployee;
import com.examplenewstack.newstack.entity.librarie.lore.Lore;
import com.examplenewstack.newstack.entity.loan.Loan;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "librarian")



public class Employee extends User {
    private TypeEmployee typeEmployee;
    public Employee() { super(); }

    //Relacionamento com Loan
    @OneToMany(mappedBy = "employee")
    private List<Loan> loans;

    //Relacionamento com Lore
    @OneToMany(mappedBy = "employee")
    private List<Lore> lores;

    public Employee(String name, String CPF, String email, String telephone, String password) {
        super(name, CPF, email, telephone, password);
    }

    public TypeEmployee getTypeEmployee() {
        return typeEmployee;
    }

    public void setTypeEmployee(TypeEmployee typeEmployee) {
        this.typeEmployee = typeEmployee;
    }

    @Override
    public User toUser() {
        return super.toUser();
    }
}
