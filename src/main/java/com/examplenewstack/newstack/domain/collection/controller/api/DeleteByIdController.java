package com.examplenewstack.newstack.domain.collection.controller.api;

import com.examplenewstack.newstack.domain.collection.service.DeleteByIdCollection;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collection")
@Tag(name = "Coleção")
public class DeleteByIdController {

    private final DeleteByIdCollection service;

    public DeleteByIdController(DeleteByIdCollection service) {
        this.service = service;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(
            @PathVariable int id
    ){
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
