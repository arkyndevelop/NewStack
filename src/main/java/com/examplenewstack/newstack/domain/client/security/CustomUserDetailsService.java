package com.examplenewstack.newstack.domain.client.security;

import com.examplenewstack.newstack.domain.admin.AdminMaster;
import com.examplenewstack.newstack.domain.admin.repository.AdminRepository;
import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.domain.client.repository.ClientRepository;
import com.examplenewstack.newstack.domain.employee.Employee;
import com.examplenewstack.newstack.domain.employee.repository.EmployeeRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;
    private final AdminRepository adminRepository;
    private final EmployeeRepository employeeRepository;

    public CustomUserDetailsService(ClientRepository clientRepository, AdminRepository adminRepository , EmployeeRepository employeeRepository) {
        this.clientRepository = clientRepository;
        this.adminRepository = adminRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        // Limpa o identificador se for CPF para garantir a busca correta
        String cleanIdentifier = identifier.replaceAll("[^0-9]", "");

        // 1. Tenta carregar como Cliente
        Client client = clientRepository.findByCPF(cleanIdentifier); // Ou findByEmail(identifier) se usar email
        if (client != null) {
            // Retorna um UserDetails. Idealmente, você teria uma classe CustomUserDetails
            // que encapsula seu Client e implementa os métodos getAuthorities, getPassword, etc.
            return new org.springframework.security.core.userdetails.User(
                    client.getCPF(), // ou client.getEmail()
                    client.getPassword(),
                    AuthorityUtils.createAuthorityList("ROLE_CLIENT") // Exemplo de role
            );
        }

        // 2. Tenta carregar como Admin
        AdminMaster admin = adminRepository.findByCPF(cleanIdentifier); // AdminMaster não tem findByCPF direto no seu repositório, mas é um exemplo
        if (admin != null) {
            return new org.springframework.security.core.userdetails.User(
                    admin.getCPF(), // ou admin.getEmail()
                    admin.getPassword(),
                    AuthorityUtils.createAuthorityList("ROLE_ADMIN")
            );
        }

//         3. (Opcional) Tenta carregar como Employee
         Employee employee = employeeRepository.findByCPF(cleanIdentifier);
         if (employee != null) {
             return new org.springframework.security.core.userdetails.User(
                 employee.getCPF(),
                 employee.getPassword(),
                     AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE")
             );
         }

        throw new UsernameNotFoundException("Usuário não encontrado com o identificador: " + identifier);
    }
}
