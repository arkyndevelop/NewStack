package com.examplenewstack.newstack.domain.client.exception.infra;


import com.examplenewstack.newstack.core.exception.RestErrorMessage;
import com.examplenewstack.newstack.domain.client.exception.CustomersRegisteredDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerCustomersDataAlreadyRegistered {
    //Manipulador de exceções responsavel por verificar dados cadastrados
    @ExceptionHandler(CustomersRegisteredDataException.class)
    private ResponseEntity<RestErrorMessage> ClientRegisteredData(CustomersRegisteredDataException registeredException) {
        String RestErrorMessage = registeredException.getMessage();
        String message = switch (RestErrorMessage) {
            case "cpf" -> "CPF ja cadastrado!";
            case "email" -> "Email ja cadastrado!";
            case "telephone" -> "Telefone ja cadastrado!";
            default -> "Dados ja cadastrados!";
        };
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new RestErrorMessage(HttpStatus.CONFLICT, message));
    }
}
