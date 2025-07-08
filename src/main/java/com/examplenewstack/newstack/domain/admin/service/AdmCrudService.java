package com.examplenewstack.newstack.domain.admin.service;

import com.examplenewstack.newstack.domain.admin.AdminMaster;
import com.examplenewstack.newstack.domain.admin.dto.AdminRequest;
import com.examplenewstack.newstack.domain.admin.repository.AdminRepository;
import com.examplenewstack.newstack.domain.client.exception.CustomersRegisteredDataException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdmCrudService {

    private final AdminRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AdmCrudService(AdminRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public AdminMaster registerAdm(AdminRequest request){
        if (repository.existsByCPF(request.CPF())) {
            throw new CustomersRegisteredDataException("CPF");
        }
        if (repository.existsByEmail(request.email())) {
            throw new CustomersRegisteredDataException("email");
        }
        if (repository.existsByTelephone(request.telephone())) {
            throw new CustomersRegisteredDataException("telephone");
        }
        AdminMaster newAdmin = request.toAdm();

        String encodedPassword = passwordEncoder.encode(request.password());
        newAdmin.setPassword(encodedPassword);
        newAdmin.setRole("ADMIN");

        return repository.save(newAdmin);
    }
}
