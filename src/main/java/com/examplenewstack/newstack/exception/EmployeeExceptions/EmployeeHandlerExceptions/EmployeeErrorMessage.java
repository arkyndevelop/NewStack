package com.examplenewstack.newstack.exception.EmployeeExceptions.EmployeeHandlerExceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class EmployeeErrorMessage {

    private HttpStatus status;
    private String message;

}
