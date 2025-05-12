package com.examplenewstack.newstack.entity.librarie.lore;

import com.examplenewstack.newstack.entity.employee.Employee;
import com.examplenewstack.newstack.entity.librarie.book.Book;
import com.examplenewstack.newstack.entity.librarie.collection.Collection;
import com.examplenewstack.newstack.entity.loan.Loan;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "lore")
public class Lore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false,length = 20)
    private String isbn;

    @Column(nullable = false)
    private int year_publication;

    @Column(nullable = false,length = 25)
    private String category;

    @Column(nullable = false)
    private Boolean availability;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime register;


    //Relacionamento com Employee
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    // Relacionamento com Collection
    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;

    //Relacionamento 1:1 book
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    //Relacionamento com Loan
    @OneToMany(mappedBy = "lore")
    private List<Loan> loans;

    @Deprecated
    public Lore() {}

    public Lore(String title, String isbn, int year_publication, String category) {
        this.title = title;
        this.isbn = isbn;
        this.year_publication = year_publication;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbm) {
        this.isbn = isbm;
    }

    public int getYear_publication() {
        return year_publication;
    }

    public void setYear_publication(int year_publication) {
        this.year_publication = year_publication;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public LocalDateTime getRegister() {
        return register;
    }

    public void setRegister(LocalDateTime register) {
        this.register = LocalDateTime.now();
    }
}