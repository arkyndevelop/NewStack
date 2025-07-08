package com.examplenewstack.newstack.domain.client.exception.infra;

import com.examplenewstack.newstack.domain.client.exception.NoCustomersFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class NoCustomersFound extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoCustomersFoundException.class)
    private ResponseEntity<RestErrorMessage> CustomersFound(NoCustomersFoundException NoCustomersFound){
            RestErrorMessage treatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND,NoCustomersFound.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(treatResponse);
    }
}
