package com.examplenewstack.newstack.pages.dto.login;

// Campos para realizar o Login
public record LoginRequest(
        String username,
        String password
) {
}
