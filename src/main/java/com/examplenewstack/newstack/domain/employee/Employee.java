package com.examplenewstack.newstack.domain.employee;

import com.examplenewstack.newstack.core.entity.User;
import com.examplenewstack.newstack.domain.book.Book;
import com.examplenewstack.newstack.domain.employee.enums.TypeEmployee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TypeEmployee typeEmployee;

    //Relacionamento com book
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Book> books;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.typeEmployee == null) {
            return List.of();
        }
        return List.of(new SimpleGrantedAuthority(this.typeEmployee.getRole()));
    }

}
