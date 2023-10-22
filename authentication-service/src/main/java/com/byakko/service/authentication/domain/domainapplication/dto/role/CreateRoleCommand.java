package com.byakko.service.authentication.domain.domainapplication.dto.role;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CreateRoleCommand {

    @NotBlank(message = "name must be not blank")
    private String name;

}
