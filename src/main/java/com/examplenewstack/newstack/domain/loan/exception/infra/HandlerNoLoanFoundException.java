package com.examplenewstack.newstack.domain.loan.exception.infra;

import com.examplenewstack.newstack.core.exception.RestErrorMessage;
import com.examplenewstack.newstack.domain.loan.exception.NoLoanFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
