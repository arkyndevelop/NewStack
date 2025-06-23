package com.examplenewstack.newstack.domain.collection.service;

import com.examplenewstack.newstack.domain.collection.Collection;
import com.examplenewstack.newstack.domain.collection.exception.NoCollectionFoundException;
import com.examplenewstack.newstack.domain.collection.repository.CollectionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteByIdCollection {

    private final CollectionRepository repository;

    public DeleteByIdCollection(CollectionRepository repository) {
        this.repository = repository;
    }

    public void deleteById(
        int collectionId
    ){
        Optional<Collection> collectionFound = repository.findById(collectionId);
        if(!collectionFound.isPresent()){
            throw new NoCollectionFoundException();
        }

        repository.deleteById(collectionId);
    }
}
