package com.examplenewstack.newstack.infra.service;

import com.examplenewstack.newstack.domain.admin.AdminMaster;
import com.examplenewstack.newstack.domain.admin.repository.AdminRepository;
import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.domain.client.repository.ClientRepository;
import com.examplenewstack.newstack.domain.employee.Employee;
import com.examplenewstack.newstack.domain.employee.repository.EmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    public CustomUserDetailsService(ClientRepository clientRepository, EmployeeRepository employeeRepository, AdminRepository adminRepository) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(
            String identifier
    ) throws UsernameNotFoundException {
        Client client = clientRepository.findByCPF(identifier);
        if (client != null){
            return client;
        }

        Employee employee = employeeRepository.findByCPF(identifier);
        if (employee != null){
            return employee;
        }

        AdminMaster adminMaster = adminRepository.findByCPF(identifier);
        if (adminMaster != null){
            return adminMaster;
        }

        throw new UsernameNotFoundException("Usuário não encontrado com o identificado: " + identifier);
    }
}
