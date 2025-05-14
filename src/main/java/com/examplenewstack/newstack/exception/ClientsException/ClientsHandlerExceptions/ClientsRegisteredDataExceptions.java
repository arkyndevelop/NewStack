package com.examplenewstack.newstack.exception.ClientsException.ClientsHandlerExceptions;


import com.examplenewstack.newstack.exception.ClientsException.RegisteredDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ClientsRegisteredDataExceptions extends ResponseEntityExceptionHandler {


    @ExceptionHandler(RegisteredDataException.class)
    private ResponseEntity<RestErrorMessage> ClientRegisteredData(RegisteredDataException registeredException){
        String RestErrorMessage = registeredException.getMessage();
        String message= switch (RestErrorMessage){
            case "cpf" -> "CPF ja cadastrado!";
            case "email" -> "Email ja cadastrado!";
            case "telephone" -> "Telefone ja cadastrado";
            default -> "Dados ja cadastrados!";

        };
         return ResponseEntity.status(HttpStatus.CONFLICT)
                 .body(new RestErrorMessage(HttpStatus.CONFLICT, message));
    }


}
