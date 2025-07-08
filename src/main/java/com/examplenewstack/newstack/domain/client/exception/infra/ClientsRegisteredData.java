package com.examplenewstack.newstack.domain.client.exception.infra;


import com.examplenewstack.newstack.domain.client.exception.ClientsRegisteredDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ClientsRegisteredData extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientsRegisteredDataException.class)
    private ResponseEntity<RestErrorMessage> ClientRegisteredData(ClientsRegisteredDataException registeredException){
        String RestErrorMessage = registeredException.getMessage();
        String message = switch (RestErrorMessage){
            case "cpf" -> "CPF ja cadastrado!";
            case "email" -> "Email ja cadastrado!";
            case "telephone" -> "Telefone ja cadastrado!";
            default -> "Dados ja cadastrados!";

        };
         return ResponseEntity.status(HttpStatus.CONFLICT)
                 .body(new RestErrorMessage(HttpStatus.CONFLICT, message));
    }
}
