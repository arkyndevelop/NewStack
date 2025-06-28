package com.examplenewstack.newstack.employee;

public enum TypeEmployee {
    LIBRARIAN("ROLE_LIBRARIAN"),
    LIBRARY_ASSISTANT("ROLE_LIBRARY_ASSISTANT"),
    RECEPTIONIST("ROLE_RECEPTIONIST");

    private final String role;

    TypeEmployee(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}