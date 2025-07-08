package com.examplenewstack.newstack.domain.collection.service;

import com.examplenewstack.newstack.domain.collection.Collection;
import com.examplenewstack.newstack.domain.collection.dto.CollectionRequestDTO;
import com.examplenewstack.newstack.domain.collection.dto.CollectionResponseDTO;
import com.examplenewstack.newstack.domain.collection.exception.NoCollectionFoundException;
import com.examplenewstack.newstack.domain.collection.repository.CollectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollectionCrudService {


    private final CollectionRepository repository;


    public CollectionCrudService(CollectionRepository collectionRepository) {
        this.repository = collectionRepository;
    }

    //Função Responsavel por criar uma Coleção
    public Collection create(
            CollectionRequestDTO request
    ){
        return repository.save(request.toCollection());
    }


    //Função responsavel por mostrar todas as Coleção
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

    //Função responsavel por mostrar uma Coleção por ID
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

    //Função responsavel por deletar uma Coleção por ID
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




