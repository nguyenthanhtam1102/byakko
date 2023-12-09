package com.byakko.service.sales.service.production.domain.domaincore.entity;

import com.byakko.service.sales.common.domain.entity.BaseEntity;
import com.byakko.service.sales.common.domain.exception.ValidationException;
import com.byakko.service.sales.service.production.domain.domaincore.valueobject.OptionValueId;

import java.util.Map;
import java.util.UUID;

public class OptionValue extends BaseEntity<OptionValueId> {

    private String name;

    private Option option;

    public OptionValue() {

    }

    public OptionValue(String name, Option option) {
        setId(new OptionValueId(UUID.randomUUID().toString()));
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

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public static final class Builder {
        private OptionValueId id;
        private String name;
        private Option option;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(OptionValueId id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder option(Option option) {
            this.option = option;
            return this;
        }

        public OptionValue build() {
            OptionValue globalOptionValue = new OptionValue();
            globalOptionValue.setId(id);
            globalOptionValue.setName(name);
            globalOptionValue.setOption(option);
            return globalOptionValue;
        }
    }
}
