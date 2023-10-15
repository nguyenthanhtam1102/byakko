package com.byakko.service.authentication.domain.domainapplication.dto.role;

import com.byakko.common.application.dto.ListAllResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class ListAllRoleResponse extends ListAllResponse<RoleResponse> {
}
