package com.byakko.common.application.dto;

public class ErrorResponse {

    private ErrorDTO error;

    public ErrorDTO getError() {
        return error;
    }

    public static final class Builder {
        private ErrorDTO error;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder error(ErrorDTO error) {
            this.error = error;
            return this;
        }

        public ErrorResponse build() {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.error = this.error;
            return errorResponse;
        }
    }
}
