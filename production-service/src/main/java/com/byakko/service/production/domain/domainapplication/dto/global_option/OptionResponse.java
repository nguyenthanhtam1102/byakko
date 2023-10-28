package com.byakko.service.production.domain.domainapplication.dto.global_option;

import java.util.List;

public class OptionResponse {

    private String id;

    private String name;
    private List<OptionValueResponse> values;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OptionValueResponse> getValues() {
        return values;
    }

    public void setValues(List<OptionValueResponse> values) {
        this.values = values;
    }

    public static final class Builder {
        private String id;
        private String name;
        private List<OptionValueResponse> values;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder values(List<OptionValueResponse> values) {
            this.values = values;
            return this;
        }

        public OptionResponse build() {
            OptionResponse optionResponse = new OptionResponse();
            optionResponse.name = this.name;
            optionResponse.values = this.values;
            optionResponse.id = this.id;
            return optionResponse;
        }
    }
}
