package com.examplenewstack.newstack.repository;

import com.examplenewstack.newstack.entity.librarie.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book , Long> {


    boolean existsByTitle(String title);
}
