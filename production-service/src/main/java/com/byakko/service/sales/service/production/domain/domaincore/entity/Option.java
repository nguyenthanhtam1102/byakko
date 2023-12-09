package com.byakko.service.sales.service.production.domain.domaincore.entity;

import com.byakko.service.sales.common.domain.entity.BaseEntity;
import com.byakko.service.sales.common.domain.exception.ValidationException;
import com.byakko.service.sales.service.production.domain.domaincore.valueobject.OptionId;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Option extends BaseEntity<OptionId> {

    private String name;

    private Set<OptionValue> values;

    public void initialize() {
        setId(new OptionId(UUID.randomUUID().toString()));
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
            values.forEach(OptionValue::validate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<OptionValue> getValues() {
        return values;
    }

    public void setValues(Set<OptionValue> values) {
        this.values = values;
    }

    public static final class Builder {
        private OptionId id;
        private String name;
        private Set<OptionValue> values;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(OptionId id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder values(Set<OptionValue> values) {
            this.values = values;
            return this;
        }

        public Option build() {
            Option option = new Option();
            option.setId(id);
            option.setName(name);
            option.setValues(values);
            return option;
        }
    }
}
