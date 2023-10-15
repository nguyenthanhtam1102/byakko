package com.byakko.service.authentication.domain.domainapplication.dto.role;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class GetRoleCommand {

    @NotNull(message = "id must be not null")
    private Long id;

}
