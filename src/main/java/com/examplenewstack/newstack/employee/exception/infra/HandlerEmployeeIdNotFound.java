package com.examplenewstack.newstack.employee.exception.infra;


import com.examplenewstack.newstack.core.exception.RestErrorMessage;
import com.examplenewstack.newstack.employee.exception.NoEmployeersFoundByIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerEmployeeIdNotFound {

    // Manipulador de exceções responsavel por verificar se existe por ID
    @ExceptionHandler(NoEmployeersFoundByIdException.class)
    private ResponseEntity<RestErrorMessage> noEmployeeFoundByID(NoEmployeersFoundByIdException noEmployeersFoundById){

        RestErrorMessage treatResponseEmployee = new RestErrorMessage(HttpStatus.NOT_FOUND,noEmployeersFoundById.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(treatResponseEmployee);

    }


}
