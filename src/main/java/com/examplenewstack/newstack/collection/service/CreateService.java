package com.examplenewstack.newstack.collection.service;

import com.examplenewstack.newstack.collection.Collection;
import com.examplenewstack.newstack.collection.dto.CollectionRequestDTO;
import com.examplenewstack.newstack.collection.repository.CollectionRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateService {

    private final CollectionRepository repository;

    public CreateService(CollectionRepository repository) {
        this.repository = repository;
    }

    public Collection create(
        CollectionRequestDTO request
    ){
        return repository.save(request.toCollection());
    }
}
