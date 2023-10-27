package com.byakko.service.production.domain.domaincore.entity;

import com.byakko.common.domain.entity.BaseEntity;
import com.byakko.common.domain.exception.ValidationException;
import com.byakko.service.production.domain.domaincore.valueobject.GlobalOptionValueId;

import java.util.Map;
import java.util.UUID;

public class GlobalOptionValue extends BaseEntity<GlobalOptionValueId> {

    private String name;

    private GlobalOption option;

    public GlobalOptionValue() {

    }

    public GlobalOptionValue(String name, GlobalOption option) {
        setId(new GlobalOptionValueId(UUID.randomUUID().toString()));
        this.name = name;
        this.option = option;
    }

    public void validate() {
        validateName();
        validateOption();
    }

    private void validateName() {
        if(name == null || name.isBlank())
            throw new ValidationException(Map.of("name", "name must be not blank"));
    }

    private void validateOption() {
        if(option == null)
            throw new ValidationException(Map.of("option", "option must be not null"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GlobalOption getOption() {
        return option;
    }

    public void setOption(GlobalOption option) {
        this.option = option;
    }

    public static final class Builder {
        private GlobalOptionValueId id;
        private String name;
        private GlobalOption option;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(GlobalOptionValueId id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder option(GlobalOption option) {
            this.option = option;
            return this;
        }

        public GlobalOptionValue build() {
            GlobalOptionValue globalOptionValue = new GlobalOptionValue();
            globalOptionValue.setId(id);
            globalOptionValue.setName(name);
            globalOptionValue.setOption(option);
            return globalOptionValue;
        }
    }
}
