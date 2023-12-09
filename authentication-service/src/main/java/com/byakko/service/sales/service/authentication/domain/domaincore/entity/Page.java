package com.byakko.service.sales.service.authentication.domain.domaincore.entity;

import com.byakko.service.sales.common.domain.entity.BaseEntity;
import com.byakko.service.sales.common.domain.exception.ValidationException;
import com.byakko.service.sales.service.authentication.domain.domaincore.valueobject.PageId;

import java.util.Map;

public class Page extends BaseEntity<PageId> {
    private String description;
    private String name;
    private String path;
    public void validate(){
        validateDescription();
        validateName();
        validatePath();
    }
    private void validateDescription(){
        if(description == null || description.isBlank())
            throw new ValidationException(Map.of("description", "description must be not blank"));
    }

    private void validateName(){
        if(name == null || name.isBlank())
            throw new ValidationException(Map.of("name", "name must be not blank"));
    }
    private void validatePath(){
        if(path == null || path.isBlank())
            throw new ValidationException(Map.of("path", "path must be not blank"));
    }
}
