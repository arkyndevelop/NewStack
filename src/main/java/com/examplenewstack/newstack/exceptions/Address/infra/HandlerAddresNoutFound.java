package com.examplenewstack.newstack.exceptions.Address.infra;


import com.examplenewstack.newstack.exceptions.Address.AddressNotFoundException;
import com.examplenewstack.newstack.exceptions.RestErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerAddresNoutFound {

    @ExceptionHandler(AddressNotFoundException.class)
    private ResponseEntity<RestErrorMessage> addressNotFound(AddressNotFoundException addressNotFoundException){
        RestErrorMessage treatErroMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, addressNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(treatErroMessage);



    }

}
