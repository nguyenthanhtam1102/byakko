package com.byakko.service.authentication.domain.domainapplication.dto.shopowner;

import com.byakko.service.authentication.domain.domainapplication.dto.SignInResponse;

public class ShopOwnerSignInResponse extends SignInResponse {


    public static final class Builder {
        private String idToken;
        private String refreshToken;
        private Long expiresTime;
        private String userId;
        private String menuId;
        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder idToken(String idToken) {
            this.idToken = idToken;
            return this;
        }
        public Builder menuId(String menuId) {
            this.menuId = menuId;
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

        public ShopOwnerSignInResponse build() {
            ShopOwnerSignInResponse shopOwnerSignInResponse = new ShopOwnerSignInResponse();
            shopOwnerSignInResponse.setIdToken(idToken);
            shopOwnerSignInResponse.setRefreshToken(refreshToken);
            shopOwnerSignInResponse.setExpiresTime(expiresTime);
            shopOwnerSignInResponse.setUserId(userId);
            shopOwnerSignInResponse.setMenuId(menuId);
            return shopOwnerSignInResponse;
        }
    }
}
