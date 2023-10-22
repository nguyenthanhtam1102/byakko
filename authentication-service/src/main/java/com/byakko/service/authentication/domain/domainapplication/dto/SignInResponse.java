package com.byakko.service.authentication.domain.domainapplication.dto;

public abstract class SignInResponse {

    protected String idToken;
    protected String refreshToken;
    protected Long expiresTime;
    protected String userId;

    public SignInResponse() {
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setExpiresTime(Long expiresTime) {
        this.expiresTime = expiresTime;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdToken() {
        return idToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public Long getExpiresTime() {
        return expiresTime;
    }

    public String getUserId() {
        return userId;
    }
}
