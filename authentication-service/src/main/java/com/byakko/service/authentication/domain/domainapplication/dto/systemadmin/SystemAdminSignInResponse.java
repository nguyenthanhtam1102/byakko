package com.byakko.service.authentication.domain.domainapplication.dto.systemadmin;

import com.byakko.service.authentication.domain.domainapplication.dto.SignInResponse;

public class SystemAdminSignInResponse extends SignInResponse {


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

        public SystemAdminSignInResponse build() {
            SystemAdminSignInResponse systemAdminSignInResponse = new SystemAdminSignInResponse();
            systemAdminSignInResponse.setIdToken(idToken);
            systemAdminSignInResponse.setRefreshToken(refreshToken);
            systemAdminSignInResponse.setExpiresTime(expiresTime);
            systemAdminSignInResponse.setUserId(userId);
            return systemAdminSignInResponse;
        }
    }
}
