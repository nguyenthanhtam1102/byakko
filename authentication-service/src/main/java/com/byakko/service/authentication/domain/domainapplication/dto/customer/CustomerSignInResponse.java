package com.byakko.service.authentication.domain.domainapplication.dto.customer;

import com.byakko.service.authentication.domain.domainapplication.dto.SignInResponse;

public class CustomerSignInResponse extends SignInResponse {

    public static final class Builder {
        private String idToken;
        private String refreshToken;
        private Long expiresTime;
        private String userId;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder idToken(String idToken) {
            this.idToken = idToken;
            return this;
        }

        public Builder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public Builder expiresTime(Long expiresTime) {
            this.expiresTime = expiresTime;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public CustomerSignInResponse build() {
            CustomerSignInResponse customerSignInResponse = new CustomerSignInResponse();
            customerSignInResponse.idToken = this.idToken;
            customerSignInResponse.expiresTime = this.expiresTime;
            customerSignInResponse.userId = this.userId;
            customerSignInResponse.refreshToken = this.refreshToken;
            return customerSignInResponse;
        }
    }
}
