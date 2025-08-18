package com.examplenewstack.newstack.domain.admin.controller;

import com.examplenewstack.newstack.domain.admin.dto.AdminRequest;
import com.examplenewstack.newstack.domain.admin.service.AdmCrudService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admins")
public class AdminCrudController {

    private final AdmCrudService service;

    public AdminCrudController(AdmCrudService service) {
        this.service = service;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid AdminRequest request){
        service.registerAdm(request);
        return ResponseEntity.ok().build();
    }
}
