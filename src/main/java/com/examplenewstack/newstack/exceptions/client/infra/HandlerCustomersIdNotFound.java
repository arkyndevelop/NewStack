package com.examplenewstack.newstack.exceptions.client.infra;


import com.examplenewstack.newstack.exceptions.RestErrorMessage;
import com.examplenewstack.newstack.exceptions.client.NoCustomersFoundByIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerCustomersIdNotFound {

    // Manipulador de exceções responsavel por verificar se existe por ID
    @ExceptionHandler(NoCustomersFoundByIdException.class)
    private ResponseEntity<RestErrorMessage> noCustomersFoundById(NoCustomersFoundByIdException noCustomersFoundByid) {
        RestErrorMessage treatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, noCustomersFoundByid.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(treatResponse);
    }
}
