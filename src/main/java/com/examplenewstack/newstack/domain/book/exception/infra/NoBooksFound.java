package com.examplenewstack.newstack.domain.book.exception.infra;

import com.examplenewstack.newstack.core.exception.RestErrorMessage;
import com.examplenewstack.newstack.domain.book.exception.NoBooksFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class NoBooksFound extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoBooksFoundException.class)
    private ResponseEntity<RestErrorMessage> bookFound(
            NoBooksFoundException noBooksFoundException
    ){
        RestErrorMessage treatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, noBooksFoundException.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(treatResponse);
    }
}
