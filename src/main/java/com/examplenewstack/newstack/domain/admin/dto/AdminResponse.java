package com.examplenewstack.newstack.domain.admin.dto;

import com.examplenewstack.newstack.domain.admin.AdminMaster;

public record AdminResponse (
        int id,
        String name,
        String CPF,
        String email
) {
    public static AdminResponse fromEntity(AdminMaster adminMaster) {
        return new AdminResponse(
                adminMaster.getId(),
                adminMaster.getName(),
                adminMaster.getCPF(),
                adminMaster.getEmail()
        );
    }
}
