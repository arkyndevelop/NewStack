package com.examplenewstack.newstack.loan.exception.infra;

import com.examplenewstack.newstack.core.exception.RestErrorMessage;
import com.examplenewstack.newstack.loan.exception.NoLoanFoundByIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerNoLoanFoundById {

    @ExceptionHandler(NoLoanFoundByIdException.class)
    private ResponseEntity<RestErrorMessage> loanNotFoundById(
            NoLoanFoundByIdException idNotFound
    ){
        RestErrorMessage treatResponse = new RestErrorMessage(
                HttpStatus.NOT_FOUND,
                idNotFound.getMessage()
        );

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(treatResponse);
    }



}
