package com.byakko.service.sales.service.authentication.domain.domainapplication.dto.response;

import lombok.Data;

@Data
public class PermissionResponse {
    private int permissions_id;
    private String code;
    private String permissions_name;
}
