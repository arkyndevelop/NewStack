package com.examplenewstack.newstack.collection.exception.infra;

import com.examplenewstack.newstack.collection.exception.NoCollectionFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerCollectionNotFound {

    @ExceptionHandler(NoCollectionFound.class)
    private ResponseEntity<RestErrorMessage> noCollectionFound(
            NoCollectionFound noCollectionFound
    ){
        RestErrorMessage treatResponse = new RestErrorMessage(
                HttpStatus.NOT_FOUND,
                noCollectionFound.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(treatResponse);
    }
}
