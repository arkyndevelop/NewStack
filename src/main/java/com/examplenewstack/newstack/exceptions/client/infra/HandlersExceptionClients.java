package com.examplenewstack.newstack.exceptions.client.infra;


import com.examplenewstack.newstack.exceptions.client.CustomersRegisteredDataException;
import com.examplenewstack.newstack.exceptions.client.NoCustomersPasswordConfirmException;
import com.examplenewstack.newstack.exceptions.client.NoCustomersFoundException;
import com.examplenewstack.newstack.exceptions.client.NoCustomersFoundByIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlersExceptionClients {
    //Manipulador de exceções responsavel por verificar dados cadastrados
    @ExceptionHandler(CustomersRegisteredDataException.class)
    private ResponseEntity<ClientErrorMessage> ClientRegisteredData(CustomersRegisteredDataException registeredException) {
        String clientErrorMessage = registeredException.getMessage();
        String message = switch (clientErrorMessage) {
            case "cpf" -> "CPF ja cadastrado!";
            case "email" -> "Email ja cadastrado!";
            case "telephone" -> "Telefone ja cadastrado!";
            default -> "Dados ja cadastrados!";

        };
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ClientErrorMessage(HttpStatus.CONFLICT, message));
    }


    // Manipulador de exceções responsavel por verificar se as senhas são iguais
    @ExceptionHandler(NoCustomersPasswordConfirmException.class)
    private ResponseEntity<ClientErrorMessage> NoClietPasswordConfirm(NoCustomersPasswordConfirmException noClientPassword) {
        ClientErrorMessage clientTreatResponse = new ClientErrorMessage(HttpStatus.UNAUTHORIZED, noClientPassword.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(clientTreatResponse);


    }
    // Manipulador de exceções responsavel por verificar se existe algo cadastrado
    @ExceptionHandler(NoCustomersFoundException.class)
    private ResponseEntity<ClientErrorMessage> CustomersFound(NoCustomersFoundException NoCustomersFound) {
        ClientErrorMessage treatResponse = new ClientErrorMessage(HttpStatus.NOT_FOUND, NoCustomersFound.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(treatResponse);
    }
    // Manipulador de exceções responsavel por verificar se existe por ID
    @ExceptionHandler(NoCustomersFoundByIdException.class)
    private ResponseEntity<ClientErrorMessage> noCustomersFoundById(NoCustomersFoundByIdException noCustomersFoundByid) {
        ClientErrorMessage treatResponse = new ClientErrorMessage(HttpStatus.NOT_FOUND, noCustomersFoundByid.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(treatResponse);
    }
}


