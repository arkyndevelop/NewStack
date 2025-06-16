package com.examplenewstack.newstack.loan.exception.infra;

import com.examplenewstack.newstack.core.exception.RestErrorMessage;
import com.examplenewstack.newstack.loan.exception.NoLoanFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class HandlerNoLoanFoundException {



    @ExceptionHandler(NoLoanFoundException.class)
    private ResponseEntity<RestErrorMessage> noLoanFound(
            NoLoanFoundException noLoanFoundException
    ){
        RestErrorMessage treatResponse = new RestErrorMessage(
                HttpStatus.NOT_FOUND,
                noLoanFoundException.getMessage()
        );

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(treatResponse);
    }




}
