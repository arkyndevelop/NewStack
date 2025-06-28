package com.examplenewstack.newstack.domain.employee.enums;

import lombok.Getter;

@Getter
public enum TypeEmployee {
    LIBRARIAN("ROLE_LIBRARIAN"),
    LIBRARY_ASSISTANT("ROLE_LIBRARY_ASSISTANT"),
    RECEPTIONIST("ROLE_RECEPTIONIST");

    private final String role;

    TypeEmployee(String role) {
        this.role = role;
    }

}