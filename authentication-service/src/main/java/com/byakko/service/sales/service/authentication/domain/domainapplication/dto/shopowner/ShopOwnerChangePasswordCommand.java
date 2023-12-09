package com.byakko.service.sales.service.authentication.domain.domainapplication.dto.shopowner;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class ShopOwnerChangePasswordCommand {
    @NotBlank(message = "id must be not blank")
    @JsonProperty("id")
    private String id;
    @NotBlank(message = "current password must be not blank")
    @JsonProperty("current_password")
    private String currentPassword;

    @NotBlank(message = "new password must be not blank")
    @JsonProperty("new_password")
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

    public void setId(String id) {
        this.id = id;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
