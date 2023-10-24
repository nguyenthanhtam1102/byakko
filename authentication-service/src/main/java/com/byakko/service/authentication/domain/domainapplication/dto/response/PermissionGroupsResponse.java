package com.byakko.service.authentication.domain.domainapplication.dto.response;

import com.byakko.service.authentication.dataaccess.entity.PermissionsEntity;
import lombok.Data;

import java.util.List;
@Data
public class PermissionGroupsResponse {
    private int id ;
    private String name;
    private List<PermissionsEntity> permissionsList;
}
