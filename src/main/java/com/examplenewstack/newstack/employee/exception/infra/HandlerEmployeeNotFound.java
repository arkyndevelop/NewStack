package com.examplenewstack.newstack.employee.exception.infra;


import com.examplenewstack.newstack.core.exception.RestErrorMessage;
import com.examplenewstack.newstack.employee.exception.NoEmployeersFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerEmployeeNotFound {
    // Manipulador de exceções responsavel por verificar se existe algo cadastrado
    @ExceptionHandler(NoEmployeersFoundException.class)
    private ResponseEntity<RestErrorMessage> noEmployeersFound(NoEmployeersFoundException noEmployeersFoundException){
        RestErrorMessage treatResponseEmployee = new RestErrorMessage(HttpStatus.NOT_FOUND, noEmployeersFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(treatResponseEmployee);
    }
}
