package com.examplenewstack.newstack.config.security;

import com.examplenewstack.newstack.domain.admin.AdminMaster;
import com.examplenewstack.newstack.domain.admin.repository.AdminRepository;
import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.domain.client.repository.ClientRepository;
import com.examplenewstack.newstack.domain.employee.Employee;
import com.examplenewstack.newstack.domain.employee.repository.EmployeeRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
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
            return new User(
                    client.getCPF(),
                    client.getPassword(),
                    AuthorityUtils.createAuthorityList("ROLE_CLIENT")
            );
        }

        Employee employee = employeeRepository.findByCPF(identifier);
        if (employee != null){
            return new User(
                    employee.getCPF(),
                    employee.getPassword(),
                    AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE")
            );
        }

        AdminMaster adminMaster = adminRepository.findByCPF(identifier);
        if (adminMaster != null){
            return new User(
                    adminMaster.getCPF(),
                    adminMaster.getPassword(),
                    AuthorityUtils.createAuthorityList("ROLE_ADMIN")
            );
        }

        throw new UsernameNotFoundException("Usuário não encontrado com o identificado: " + identifier);
    }
}
