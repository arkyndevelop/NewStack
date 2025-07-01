package com.examplenewstack.newstack.domain.employee.service;

import com.examplenewstack.newstack.domain.employee.Employee;
import com.examplenewstack.newstack.domain.employee.dto.EmployeeRequest;
import com.examplenewstack.newstack.domain.employee.dto.EmployeeResponse;
import com.examplenewstack.newstack.domain.employee.exception.EmployeersRegisteredDataException;
import com.examplenewstack.newstack.domain.employee.exception.EmployeersSamePasswordException;
import com.examplenewstack.newstack.domain.employee.exception.NoEmployeersFoundByIdException;
import com.examplenewstack.newstack.domain.employee.exception.NoEmployeersFoundException;
import com.examplenewstack.newstack.domain.employee.repository.EmployeeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeCrudService {

    private final EmployeeRepository repository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeCrudService(EmployeeRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Employee registerEmployee(EmployeeRequest employeeDTO) {
        if(repository.existsByCPF(employeeDTO.CPF())){
            throw new EmployeersRegisteredDataException("cpf");
        }

        if(repository.existsByCPF(employeeDTO.CPF())){
            throw new EmployeersRegisteredDataException("cpf");
        }
        if (repository.existsByEmail(employeeDTO.email())) {
            throw new EmployeersRegisteredDataException("email");
        }
        if(repository.existsByTelephone(employeeDTO.telephone())){
            throw new EmployeersRegisteredDataException("telephone");
        }

        Employee newEmployee = employeeDTO.toEmployee();

        String encodedPassword = passwordEncoder.encode(employeeDTO.password());
        newEmployee.setPassword(encodedPassword);

        return repository.save(newEmployee);
    }

    public List<EmployeeResponse> reportsAllEmployee() {
        List<Employee> employeeList = repository.findAll();
        if (employeeList.isEmpty()) {
            throw new NoEmployeersFoundException();
        }

        return employeeList
                .stream()
                .map(employee -> new EmployeeResponse(
                        employee.getId(),
                        employee.getName(),
                        employee.getCPF(),
                        employee.getEmail(),
                        employee.getTelephone(),
                        employee.getTypeEmployee()))
                .toList();
    }

    public EmployeeResponse reportsEmployeeById(int id) {
        Optional<Employee> employee = repository.findById(id);
        if (employee.isEmpty()) {
            throw new NoEmployeersFoundByIdException();
        }
        return new EmployeeResponse(
                employee.get().getId(),
                employee.get().getName(),
                employee.get().getCPF(),
                employee.get().getEmail(),
                employee.get().getTelephone(),
                employee.get().getTypeEmployee()
        );
    }

    public ResponseEntity<Employee> updateEmployee(EmployeeRequest request, Integer id){
        Optional<Employee> employeeExisting = repository.findById(id);
        if (employeeExisting.isEmpty()) {
            throw new NoEmployeersFoundByIdException();
        }
        Employee employee = repository.getReferenceById(id);

        employee.setName(request.name());
        employee.setCPF(request.CPF());
        employee.setEmail(request.email());
        employee.setTelephone(request.telephone());

        if (!Objects.equals(request.password(), request.confirmPassword())) {
            throw new EmployeersSamePasswordException();
        }
        employee.setPassword(request.password());

        Employee updateEmployee = repository.save(employee);
        return ResponseEntity.ok(updateEmployee);
    }

    public void deleteAllEmployee(){
        List<Employee> employeeList =  this.repository.findAll();
        if(employeeList.isEmpty()){
            throw new NoEmployeersFoundException();
        }
        repository.deleteAll();
    }

    public ResponseEntity<Employee> deleteEmployeeById(int id){
        Optional<Employee> employee = repository.findById(id);
        if (employee.isEmpty()) {
            throw new NoEmployeersFoundByIdException();
        }

        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}