package com.examplenewstack.newstack.exceptions.employee.infra;


import com.examplenewstack.newstack.exceptions.employee.EmployeeRegisteredDataException;
import com.examplenewstack.newstack.exceptions.employee.NoEmployeePasswordConfirmException;
import com.examplenewstack.newstack.exceptions.employee.NoEmployeersFoundByIdException;
import com.examplenewstack.newstack.exceptions.employee.NoEmployeersFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlersExceptionsEmployee {


    //Manipulador de exceções responsavel por verificar dados cadastrados
    @ExceptionHandler(EmployeeRegisteredDataException.class)
    private ResponseEntity<EmployeeErrorMessage> EmployeeRegisteredData(EmployeeRegisteredDataException employeeRegisteredDataException){
        String EmployeeErrorMessage = employeeRegisteredDataException.getMessage();
        String message = switch (EmployeeErrorMessage){
            case "cpf" -> "CPF ja cadastrado!";
            case "email" -> "Email ja cadastrado!";
            case "telephone" -> "Telefone ja cadastrado!";
            default -> "Dados ja cadastrado!";

        };
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new EmployeeErrorMessage(HttpStatus.CONFLICT, message));

    }

    // Manipulador de exceções responsavel por verificar se existe por ID
    @ExceptionHandler(NoEmployeersFoundByIdException.class)
    private ResponseEntity<EmployeeErrorMessage> noEmployeeFoundByID(NoEmployeersFoundByIdException noEmployeersFoundById){

        EmployeeErrorMessage treatResponseEmployee = new EmployeeErrorMessage(HttpStatus.NOT_FOUND,noEmployeersFoundById.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(treatResponseEmployee);

    }

    // Manipulador de exceções responsavel por verificar se existe algo cadastrado
    @ExceptionHandler(NoEmployeersFoundException.class)
    private ResponseEntity<EmployeeErrorMessage> noEmployeersFound(NoEmployeersFoundException noEmployeersFoundException){
        EmployeeErrorMessage treatResponseEmployee = new EmployeeErrorMessage(HttpStatus.NOT_FOUND, noEmployeersFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(treatResponseEmployee);
    }


    // Manipulador de exceções responsavel por verificar se as senhas são iguais
    @ExceptionHandler(NoEmployeePasswordConfirmException.class)
    private ResponseEntity<EmployeeErrorMessage> noPasswordConfirm(NoEmployeePasswordConfirmException passwordExceptions){
        EmployeeErrorMessage treatResponseEmployee = new EmployeeErrorMessage(HttpStatus.UNAUTHORIZED,passwordExceptions.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(treatResponseEmployee);

    }
}
