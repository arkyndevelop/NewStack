package com.examplenewstack.newstack.domain.collection.exception.infra;

import com.examplenewstack.newstack.domain.collection.exception.NoCollectionFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerCollectionNotFound {

    @ExceptionHandler(NoCollectionFoundException.class)
    private ResponseEntity<RestErrorMessage> noCollectionFound(
            NoCollectionFoundException noCollectionFoundException
    ){
        RestErrorMessage treatResponse = new RestErrorMessage(
                HttpStatus.NOT_FOUND,
                noCollectionFoundException.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(treatResponse);
    }
}
