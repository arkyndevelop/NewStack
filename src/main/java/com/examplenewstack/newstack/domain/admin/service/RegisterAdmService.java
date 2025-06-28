package com.examplenewstack.newstack.domain.admin.service;

import com.examplenewstack.newstack.domain.admin.AdminMaster;
import com.examplenewstack.newstack.domain.admin.dto.AdminRequest;
import com.examplenewstack.newstack.domain.admin.repository.AdminRepository;
import com.examplenewstack.newstack.domain.client.exception.CustomersRegisteredDataException;

public class RegisterAdmService {

    private final AdminRepository repository;

    public RegisterAdmService(AdminRepository repository) {
        this.repository = repository;
    }

    public AdminMaster registerClient(
            AdminRequest request
    ){
        if (repository.existsByCPF(request.CPF())) {
            throw new CustomersRegisteredDataException("CPF");
        }
        if (repository.existsByEmail(request.email())) {
            throw new CustomersRegisteredDataException("email");
        }
        if (repository.existsByTelephone(request.telephone())) {
            throw new CustomersRegisteredDataException("telephone");
        }
        return repository.save(request.toAdm());
    }
}
