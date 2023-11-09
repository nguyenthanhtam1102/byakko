package com.byakko.service.authentication.domain.domainapplication.dto.shopowner;

public class ShopOwnerSignUpResponse {

    private String userId;
    private String username;
    private Long createdAt;


    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public String getUsername() {
        return username;
    }
}
