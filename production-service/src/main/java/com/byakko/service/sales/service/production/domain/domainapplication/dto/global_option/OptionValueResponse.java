package com.byakko.service.sales.service.production.domain.domainapplication.dto.global_option;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OptionValueResponse {

    private String id;
    private String name;

    @JsonProperty("option_id")
    private String optionId;

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

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public static final class Builder {
        private String id;
        private String name;
        private String optionId;

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

        public Builder optionId(String optionId) {
            this.optionId = optionId;
            return this;
        }

        public OptionValueResponse build() {
            OptionValueResponse optionValueResponse = new OptionValueResponse();
            optionValueResponse.name = this.name;
            optionValueResponse.optionId = this.optionId;
            optionValueResponse.id = this.id;
            return optionValueResponse;
        }
    }
}
