package com.byakko.service.authentication.domain.domainapplication.dto;

public abstract class SignInResponse {

    protected String idToken;
    protected String refreshToken;
    protected Long expiresTime;
    protected String userId;


}
