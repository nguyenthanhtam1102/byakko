package com.byakko.service.authentication.domain.domaincore.entity;

import com.byakko.common.DomainConstants;
import com.byakko.common.domain.entity.BaseEntity;
import com.byakko.common.domain.exception.ValidationException;
import com.byakko.service.authentication.domain.domaincore.valueobject.RoleId;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

public class Role extends BaseEntity<RoleId> {

    private String name;

    public void initialize() {
        setId(new RoleId(String.valueOf(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)).toEpochSecond())));
    }

    public void validate() {
        validateName();
    }

    private void validateName() {
        if(this.name == null || this.name.isBlank())
            throw new ValidationException(Map.of("name", "name must be not blank"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static final class Builder {
        private String name;
        private RoleId id;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder id(RoleId id) {
            this.id = id;
            return this;
        }

        public Role build() {
            Role role = new Role();
            role.setName(name);
            role.setId(id);
            return role;
        }
    }
}
