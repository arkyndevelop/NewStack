package com.examplenewstack.newstack.collection.controller.api;

import com.examplenewstack.newstack.collection.Collection;
import com.examplenewstack.newstack.collection.dto.CollectionRequestDTO;
import com.examplenewstack.newstack.collection.service.CreateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collection")
@Tag(name = "Coleção")
public class CreateCollection {

    private final CreateService service;

    public CreateCollection(CreateService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(
            @RequestBody @Valid CollectionRequestDTO requestDTO
    ) {
        Collection collection = service.create(requestDTO);
        return ResponseEntity.ok().body(collection);
    }
}
