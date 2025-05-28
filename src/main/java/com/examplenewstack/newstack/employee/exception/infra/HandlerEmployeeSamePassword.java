package com.examplenewstack.newstack.employee.exception.infra;

import com.examplenewstack.newstack.core.exception.RestErrorMessage;
import com.examplenewstack.newstack.employee.exception.EmployeersSamePasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerEmployeeSamePassword {

    // Manipulador de exceções responsavel por verificar se as senhas são iguais
    @ExceptionHandler(EmployeersSamePasswordException.class)
    private ResponseEntity<RestErrorMessage> noPasswordConfirm(EmployeersSamePasswordException passwordExceptions){
        RestErrorMessage treatResponseEmployee = new RestErrorMessage(HttpStatus.UNAUTHORIZED,passwordExceptions.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(treatResponseEmployee);

    }
}
