package com.byakko.service.sales.service.authentication.dataaccess.mapper;

import com.byakko.service.sales.service.authentication.dataaccess.entity.TokenEntity;
import com.byakko.service.sales.service.authentication.domain.domaincore.valueobject.Token;

public class TokenMapper {

    public static Token toOTP(TokenEntity otpEntity) {
        System.out.println(otpEntity);
        return Token.Builder.builder()
                .userId(otpEntity.getUserId())
                .randomData(otpEntity.getRandomData())
                .hashedData(otpEntity.getHashedData())
                .createdAt(otpEntity.getCreatedAt())
                .expiredTime(otpEntity.getExpiredTime())
                .used(otpEntity.getUsed())
                .build();
    }

    public static TokenEntity toOTPEntity(Token token) {
        return TokenEntity.builder()
                .userId(token.getUserId())
                .randomData(token.getRandomData())
                .hashedData(token.getHashedData())
                .createdAt(token.getCreatedAt())
                .expiredTime(token.getExpiredTime())
                .used(token.getUsed())
                .build();
    }

}
