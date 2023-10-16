package com.byakko.service.authentication.domain.domainapplication.dto.customer;

import javax.validation.constraints.NotBlank;

public class CustomerChangePasswordCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

    @NotBlank(message = "current password must be not blank")
    private String currentPassword;

    @NotBlank(message = "new password must be not blank")
    private String newPassword;

    public String getId() {
        return id;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
