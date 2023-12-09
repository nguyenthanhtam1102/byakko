package com.byakko.service.sales.common.application.dto;

import java.util.List;

public class ErrorDTO {

    private Integer code;
    private String message;
    private List<ErrorDetailDTO> errors;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<ErrorDetailDTO> getErrors() {
        return errors;
    }

    public static final class Builder {
        private Integer code;
        private String message;
        private List<ErrorDetailDTO> errors;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder code(Integer code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder errors(List<ErrorDetailDTO> errors) {
            this.errors = errors;
            return this;
        }

        public ErrorDTO build() {
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.code = this.code;
            errorDTO.message = this.message;
            errorDTO.errors = this.errors;
            return errorDTO;
        }
    }
}
