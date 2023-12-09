package com.byakko.service.sales.service.authentication.domain.domainapplication.dto.role;

import com.byakko.service.sales.common.application.dto.ListAllResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class ListAllRoleResponse extends ListAllResponse<RoleResponse> {
}
