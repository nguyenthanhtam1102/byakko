package com.byakko.service.sales.service.authentication.domain.domainapplication.dto.role;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateRoleCommand {

    @NotNull(message = "id must be not null")
    private String id;
    @NotBlank(message = "name must be not blank")
    private String name;
    @NotNull(message = "menu id must be not blank")
    private int menuID;
}
