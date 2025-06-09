package com.examplenewstack.newstack.collection.service;

import com.examplenewstack.newstack.collection.Collection;
import com.examplenewstack.newstack.collection.exception.NoCollectionFound;
import com.examplenewstack.newstack.collection.repository.CollectionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteByIdService {

    private final CollectionRepository repository;

    public DeleteByIdService(CollectionRepository repository) {
        this.repository = repository;
    }

    public void deleteById(
        int collectionId
    ){
        Optional<Collection> collectionFound = repository.findById(collectionId);
        if(!collectionFound.isPresent()){
            throw new NoCollectionFound();
        }

        repository.deleteById(collectionId);
    }
}
