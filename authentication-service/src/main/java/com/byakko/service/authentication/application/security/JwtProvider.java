package com.byakko.service.authentication.application.security;

import com.byakko.common.utils.JwtPayload;
import com.byakko.common.domain.valueobject.SystemRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.util.Date;
import java.util.Set;

@Slf4j
@Component
public class JwtProvider {

    @Value("${jwt.expiration-time}")
    private Long EXPIRATION_TIME;

    @Value("${jwt.token-prefix}")
    private String TOKEN_PREFIX;

    public String generateToken() {
        try {
            Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

            PrivateKey privateKey = KeyUtils.getPrivateKeyFromString("");

            JwtPayload payload = JwtPayload.Builder.builder()
                    .userId(customer.getId())
                    .authorities(Set.of(SystemRole.CUSTOMER))
                    .build();

            String token = Jwts.builder()
                    .setSubject()
                    .setIssuedAt(new Date())
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.RS256, privateKey)
                    .compact();

            return TOKEN_PREFIX + " " + token;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
