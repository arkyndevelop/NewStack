package com.examplenewstack.newstack.model.employee;

import com.examplenewstack.newstack.model.User;
import com.examplenewstack.newstack.model.enums.TypeEmployee;
import jakarta.persistence.*;

@Entity
@Table(name = "librarian")
public class Employee extends User {
    private TypeEmployee typeEmployee;
    public Employee() { super(); }

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
