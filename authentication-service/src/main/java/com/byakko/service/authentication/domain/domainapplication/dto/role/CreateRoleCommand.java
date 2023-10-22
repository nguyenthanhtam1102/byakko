package com.byakko.service.authentication.domain.domainapplication.dto.role;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CreateRoleCommand {

    @NotBlank(message = "name must be not blank")
    @JsonProperty("name")
    private String name;

    @JsonProperty("menu_id")
    private String menuId;

}
