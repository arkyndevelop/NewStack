package com.examplenewstack.newstack.exceptions.book.infra;

import com.examplenewstack.newstack.exceptions.RestErrorMessage;
import com.examplenewstack.newstack.exceptions.book.NoBooksFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HadlerNoBooksFound {

    @ExceptionHandler(NoBooksFoundException.class)
    private ResponseEntity<RestErrorMessage> noBooksFound(
            NoBooksFoundException noBooksFoundException
    ) {
        RestErrorMessage treatResponse = new RestErrorMessage(HttpStatus.NOT_FOUND, noBooksFoundException.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(treatResponse);
    }
}
