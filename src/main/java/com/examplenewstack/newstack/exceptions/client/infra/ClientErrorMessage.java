package com.examplenewstack.newstack.exceptions.client.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class ClientErrorMessage {
    private HttpStatus status;
    private String message;
}
