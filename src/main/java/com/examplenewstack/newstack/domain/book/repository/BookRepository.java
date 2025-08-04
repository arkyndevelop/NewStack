package com.examplenewstack.newstack.domain.book.repository;

import com.examplenewstack.newstack.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, PagingAndSortingRepository<Book, Integer> {

    boolean existsByTitle(String title);

    Optional<Book> findByISBN(String isbn);

    boolean existsByISBN(String isbn);
}
