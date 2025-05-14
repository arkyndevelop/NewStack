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
        String RestErrorMessage = "Dados ja cadastrados";
        if(registeredException.getMessage().contains("cpf")){
            RestErrorMessage = "cpf ja cadastrado";
        }
        if(registeredException.getMessage().contains("email")){
            RestErrorMessage = "email ja cadastrado";
        }
        if(registeredException.getMessage().contains("telefone")){
            RestErrorMessage = "telefone ja cadastrado";
        }
          RestErrorMessage treatResponse = new RestErrorMessage(HttpStatus.CONFLICT , registeredException.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(treatResponse);
    }


}
