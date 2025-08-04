package com.examplenewstack.newstack.domain.employee.service;

import com.examplenewstack.newstack.domain.client.Client;
import com.examplenewstack.newstack.domain.client.exception.NoCustomersFoundByIdException;
import com.examplenewstack.newstack.domain.employee.Employee;
import com.examplenewstack.newstack.domain.employee.dto.EmployeeRequest;
import com.examplenewstack.newstack.domain.employee.dto.EmployeeResponse;
import com.examplenewstack.newstack.domain.employee.dto.EmployeeResponseProfileDetails;
import com.examplenewstack.newstack.domain.employee.exception.EmployeersRegisteredDataException;
import com.examplenewstack.newstack.domain.employee.exception.NoEmployeersFoundByIdException;
import com.examplenewstack.newstack.domain.employee.repository.EmployeeRepository;
import com.examplenewstack.newstack.domain.loan.Loan;
import com.examplenewstack.newstack.domain.loan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeCrudService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoanRepository loanRepository;

    @Autowired
    public EmployeeCrudService(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder, LoanRepository loanRepository) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.loanRepository = loanRepository;
    }

    @Transactional
    public Employee registerEmployee(EmployeeRequest request) {
        if (employeeRepository.existsByCPF(request.CPF())) {
            throw new EmployeersRegisteredDataException("CPF já cadastrado no sistema.");
        }
        if (employeeRepository.existsByEmail(request.email())) {
            throw new EmployeersRegisteredDataException("Email já cadastrado no sistema.");
        }

        Employee newEmployee = new Employee();
        newEmployee.setName(request.name());
        newEmployee.setCPF(request.CPF());
        newEmployee.setEmail(request.email());
        newEmployee.setTelephone(request.telephone());
        newEmployee.setTypeEmployee(request.typeEmployee());
        newEmployee.setPassword(passwordEncoder.encode(request.password()));

        newEmployee.setRole(request.typeEmployee().name());

        return employeeRepository.save(newEmployee);
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponse> findAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(EmployeeResponse::fromEntity)
                .collect(Collectors.toList());
    }

    //Função responsavel por mostrar os funcionarios com paginação
    
    public Page<EmployeeResponse> getFilteredEmployees(Integer page, Integer size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        Page<Employee> foundEmployees = employeeRepository.findAll(pageRequest);
        return foundEmployees.map(EmployeeResponse::fromEntity);
    }

    @Transactional(readOnly = true)
    public EmployeeResponseProfileDetails getEmployeeProfileById(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoEmployeersFoundByIdException());
        return EmployeeResponseProfileDetails.fromEntity(employee);
    }

    @Transactional
    public void updateEmployeeByAdmin(int id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoEmployeersFoundByIdException());

        employee.setName(request.name());
        employee.setTelephone(request.telephone());
        employee.setEmail(request.email());
        employee.setTypeEmployee(request.typeEmployee());

        // Opcional: Adicionar lógica para alteração de senha se necessário
        if (request.password() != null && !request.password().isEmpty()) {
            employee.setPassword(passwordEncoder.encode(request.password()));
        }

        employeeRepository.save(employee);
    }

    @Transactional
    public void deleteEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new NoCustomersFoundByIdException("Colaborador com ID " + id + " não encontrado."));

        List<Loan> activeLoans = loanRepository.findActiveLoansByClientId(id);
        if (!activeLoans.isEmpty()) {
            throw new IllegalStateException("Não é possível excluir um cliente com empréstimos ativos. Por favor, verifique as devoluções.");
        }

        employeeRepository.delete(employee);
    }
}