package com.examplenewstack.newstack.collection.service;

import com.examplenewstack.newstack.collection.Collection;
import com.examplenewstack.newstack.collection.dto.CollectionResponseDTO;
import com.examplenewstack.newstack.collection.exception.NoCollectionFoundException;
import com.examplenewstack.newstack.collection.repository.CollectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportAllCollection {

    private final CollectionRepository repository;

    public ReportAllCollection(CollectionRepository repository) {
        this.repository = repository;
    }

    public List<CollectionResponseDTO> reportAll(){
        List<Collection> collectionFound = repository.findAll();
        if(collectionFound.isEmpty()){
            throw new NoCollectionFoundException();
        }

        return collectionFound
                .stream()
                .map(collection -> new CollectionResponseDTO(
                        collection.getTotal_quantity(),
                        collection.getTotal_available(),
                        collection.getLocation()))
                .toList();
    }
}
