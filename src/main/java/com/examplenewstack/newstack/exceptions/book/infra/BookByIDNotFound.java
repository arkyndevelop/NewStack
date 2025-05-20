package com.examplenewstack.newstack.exceptions.book.infra;

import com.examplenewstack.newstack.exceptions.RestErrorMessage;
import com.examplenewstack.newstack.exceptions.book.NoBooksFoundByIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookByIDNotFound {

    @ExceptionHandler(NoBooksFoundByIdException.class)
    private ResponseEntity<RestErrorMessage> bookByIdNotFound(
            NoBooksFoundByIdException noBooksFoundByIdException
    ){
        RestErrorMessage treatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, noBooksFoundByIdException.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(treatResponse);
    }
}