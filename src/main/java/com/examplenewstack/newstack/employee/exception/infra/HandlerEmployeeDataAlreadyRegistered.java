package com.examplenewstack.newstack.employee.exception.infra;

import com.examplenewstack.newstack.core.exception.RestErrorMessage;
import com.examplenewstack.newstack.employee.exception.EmployeersRegisteredDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerEmployeeDataAlreadyRegistered {
    //Manipulador de exceções responsavel por verificar dados cadastrados
    @ExceptionHandler(EmployeersRegisteredDataException.class)
    private ResponseEntity<RestErrorMessage> EmployeeRegisteredData(EmployeersRegisteredDataException employeersRegisteredDataException){
        String RestErrorMessage = employeersRegisteredDataException.getMessage();
        String message = switch (RestErrorMessage){
            case "cpf" -> "CPF ja cadastrado!";
            case "email" -> "Email ja cadastrado!";
            case "telephone" -> "Telefone ja cadastrado!";
            default -> "Dados ja cadastrado!";

        };
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new RestErrorMessage(HttpStatus.CONFLICT, message));

    }

}
