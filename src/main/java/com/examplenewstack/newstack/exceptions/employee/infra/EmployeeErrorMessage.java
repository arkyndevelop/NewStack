package com.examplenewstack.newstack.exceptions.employee.infra;

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
