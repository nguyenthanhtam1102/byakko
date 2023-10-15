package com.byakko.common.application.dto;

public class ErrorDetailDTO {

    private String message;
    private String domain;
    private String reason;

    public String getMessage() {
        return message;
    }

    public String getDomain() {
        return domain;
    }

    public String getReason() {
        return reason;
    }

    public static final class Builder {
        private String message;
        private String domain;
        private String reason;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public Builder reason(String reason) {
            this.reason = reason;
            return this;
        }

        public ErrorDetailDTO build() {
            ErrorDetailDTO errorDetailDTO = new ErrorDetailDTO();
            errorDetailDTO.domain = this.domain;
            errorDetailDTO.reason = this.reason;
            errorDetailDTO.message = this.message;
            return errorDetailDTO;
        }
    }
}
