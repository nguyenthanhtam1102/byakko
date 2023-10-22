package com.byakko.service.authentication.domain.domainapplication.dto.shopowner;

public class ShopOwnerSignUpResponse {

    private String userId;
    private String username;
    private Long createdAt;


    public static final class Builder {
        private String userId;
        private String username;
        private Long createdAt;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder createdAt(Long createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public ShopOwnerSignUpResponse build() {
            ShopOwnerSignUpResponse shopOwnerSignUpResponse = new ShopOwnerSignUpResponse();
            shopOwnerSignUpResponse.userId = this.userId;
            shopOwnerSignUpResponse.createdAt = this.createdAt;
            shopOwnerSignUpResponse.username = this.username;
            return shopOwnerSignUpResponse;
        }
    }
}
