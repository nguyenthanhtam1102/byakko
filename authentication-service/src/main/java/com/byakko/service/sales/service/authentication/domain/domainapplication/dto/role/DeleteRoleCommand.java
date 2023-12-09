package com.byakko.service.sales.service.authentication.domain.domainapplication.dto.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class DeleteRoleCommand {

    @NotNull(message = "id must be not null")
    private String id;

}
