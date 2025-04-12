package com.examplenewstack.newstack.repository;

import com.examplenewstack.newstack.model.librarie.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book , Long> {



}
