package com.examplenewstack.newstack.employee.exception.infra;


import com.examplenewstack.newstack.employee.exception.EmployeeRegisteredDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class EmployeeRegisteredDataExceptions {

    private ResponseEntity<RestErrorMessage> EmployeeRegisteredData(EmployeeRegisteredDataException employeeRegisteredDataException){
        String EmployeeErrorMessage = employeeRegisteredDataException.getMessage();
        String message = switch (EmployeeErrorMessage){
            case "cpf" -> "CPF ja cadastrado!";
            case "email" -> "Email ja cadastrado!";
            case "telephone" -> "Telefone ja cadastrado!";
            default -> "Dados ja cadastrado!";

        };

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new RestErrorMessage(HttpStatus.CONFLICT, message));




    }
}
