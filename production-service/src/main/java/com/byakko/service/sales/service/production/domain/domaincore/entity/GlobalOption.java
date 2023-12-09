package com.byakko.service.sales.service.production.domain.domaincore.entity;

import com.byakko.service.sales.common.domain.entity.BaseEntity;
import com.byakko.service.sales.common.domain.exception.ValidationException;
import com.byakko.service.sales.service.production.domain.domaincore.valueobject.GlobalOptionId;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class GlobalOption extends BaseEntity<GlobalOptionId> {

    private String name;

    private Set<GlobalOptionValue> values;

    public void initialize() {
        setId(new GlobalOptionId(UUID.randomUUID().toString()));
    }

    public void validate() {
        validateName();
        validateOptionValue();
    }

    private void validateName() {
        if(name == null || name.isBlank())
            throw new ValidationException(Map.of("name", "name must be not blank"));
    }

    private void validateOptionValue() {
        if(values != null && !values.isEmpty())
            values.forEach(GlobalOptionValue::validate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<GlobalOptionValue> getValues() {
        return values;
    }

    public void setValues(Set<GlobalOptionValue> values) {
        this.values = values;
    }

    public static final class Builder {
        private GlobalOptionId id;
        private String name;
        private Set<GlobalOptionValue> values;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(GlobalOptionId id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder values(Set<GlobalOptionValue> values) {
            this.values = values;
            return this;
        }

        public GlobalOption build() {
            GlobalOption globalOption = new GlobalOption();
            globalOption.setId(id);
            globalOption.setName(name);
            globalOption.setValues(values);
            return globalOption;
        }
    }
}
