package com.examplenewstack.newstack.exceptions.client.infra;


import com.examplenewstack.newstack.exceptions.RestErrorMessage;
import com.examplenewstack.newstack.exceptions.client.CustomersSamePasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerCustomersSamePassword {

    // Manipulador de exceções responsavel por verificar se as senhas são iguais
    @ExceptionHandler(CustomersSamePasswordException.class)
    private ResponseEntity<RestErrorMessage> NoClietPasswordConfirm(CustomersSamePasswordException noClientPassword) {
        RestErrorMessage clientTreatResponse = new RestErrorMessage(HttpStatus.UNAUTHORIZED, noClientPassword.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(clientTreatResponse);

    }
}
