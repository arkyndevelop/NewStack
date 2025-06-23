package com.examplenewstack.newstack.domain.collection.repository;

import com.examplenewstack.newstack.domain.collection.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Integer> {
}
