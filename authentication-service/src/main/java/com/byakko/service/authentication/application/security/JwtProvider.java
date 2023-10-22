package com.byakko.service.authentication.application.security;

import com.byakko.common.DomainConstants;
import com.byakko.common.utils.JwtPayload;
import com.byakko.common.domain.valueobject.SystemRole;
import com.byakko.common.utils.KeyUtils;
import com.byakko.service.authentication.domain.domaincore.entity.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.expiration-time}")
    private Long EXPIRATION_TIME;

    @Value("${jwt.private-key}")
    private String PRIVATE_KEY;

    private final ObjectMapper objectMapper;

    public String generateToken(User user) {
        try {
            ZonedDateTime expiredTime = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)).plusSeconds(EXPIRATION_TIME);

            PrivateKey privateKey = KeyUtils.getPrivateKeyFromString(PRIVATE_KEY);

            JwtPayload payload = null;

            if(user instanceof Customer) {
                payload = JwtPayload.Builder.builder()
                        .userId(user.getId().getValue())
                        .authorities(Set.of(SystemRole.CUSTOMER))
                        .build();
            } else if(user instanceof Employee) {
//                payload = JwtPayload.Builder.builder()
//                        .userId(user.getId().getValue())
//                        .authorities()
//                        .build();
            } else if(user instanceof SystemAdmin) {
                payload = JwtPayload.Builder.builder()
                        .userId(user.getId().getValue())
                        .authorities(Set.of(SystemRole.SYSTEM_ADMIN))
                        .build();
            } else if(user instanceof ShopOwner) {
                payload = JwtPayload.Builder.builder()
                        .userId(user.getId().getValue())
                        .authorities(Set.of(SystemRole.SHOP_OWNER))
                        .build();
            }

            return Jwts.builder()
                    .setSubject(objectMapper.writeValueAsString(payload))
                    .setIssuedAt(new Date())
                    .setExpiration(Date.from(expiredTime.toInstant()))
                    .signWith(SignatureAlgorithm.RS256, privateKey)
                    .compact();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
