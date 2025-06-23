package com.examplenewstack.newstack.domain.collection.service;

import com.examplenewstack.newstack.domain.collection.Collection;
import com.examplenewstack.newstack.domain.collection.dto.CollectionResponseDTO;
import com.examplenewstack.newstack.domain.collection.exception.NoCollectionFoundException;
import com.examplenewstack.newstack.domain.collection.repository.CollectionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportByIdCollection {

    private final CollectionRepository repository;

    public ReportByIdCollection(CollectionRepository repository) {
        this.repository = repository;
    }

    public CollectionResponseDTO reportById(
        int collectionId
    ){
        Optional<Collection> collectionFound = repository.findById(collectionId);
        if (!collectionFound.isPresent()){
            throw new NoCollectionFoundException();
        }

        return new CollectionResponseDTO(
                collectionFound.get().getTotal_quantity(),
                collectionFound.get().getTotal_available(),
                collectionFound.get().getLocation()
        );
    }
}
