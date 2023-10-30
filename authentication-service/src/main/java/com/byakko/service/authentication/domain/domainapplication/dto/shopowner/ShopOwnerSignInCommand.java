package com.byakko.service.authentication.domain.domainapplication.dto.shopowner;

import com.byakko.service.authentication.domain.domainapplication.dto.SignInCommand;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
public class ShopOwnerSignInCommand extends SignInCommand {

    @NotBlank(message = "phone or email be not blank")
    @JsonProperty("phone_or_email")
    private String phoneOrEmail;

    @NotBlank(message = "password must be not blank")
    @JsonProperty("Password")
    private String password;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneOrEmail(String phoneOrEmail) {
        this.phoneOrEmail = phoneOrEmail;
    }

    public String getPhoneOrEmail() {
        return phoneOrEmail;
    }

}
