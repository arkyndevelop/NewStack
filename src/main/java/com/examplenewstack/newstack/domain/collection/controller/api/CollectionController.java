package com.examplenewstack.newstack.domain.collection.controller.api;

import com.examplenewstack.newstack.domain.collection.Collection;
import com.examplenewstack.newstack.domain.collection.dto.CollectionRequestDTO;
import com.examplenewstack.newstack.domain.collection.service.CollectionCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collection")
@Tag(name = "Coleção")
public class CollectionController {

    private final CollectionCrudService collectionCrudService;

    public CollectionController(CollectionCrudService collectionCrudService) {
        this.collectionCrudService = collectionCrudService;
    }

    //Controller responsável pelo endpoint de criar
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CollectionRequestDTO requestDTO) {
        Collection collection = collectionCrudService.create(requestDTO);
        return ResponseEntity.ok().body(collection);
    }

    //Controller responsável por deletar por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        collectionCrudService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    //Controller responsável por criar o endpoint de mostrar todos
    @GetMapping("/report/all")
    public ResponseEntity<?> reportAll(){
        return ResponseEntity.ok().body(collectionCrudService.reportAll());
    }

    //Controller responsável por criar o endpoint de mostrar por ID
    @GetMapping("/report/{id}")
    public ResponseEntity<?> reportById(@PathVariable int id){
        return ResponseEntity.ok().body(collectionCrudService.reportById(id));
    }
}
