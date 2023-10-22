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

    @Value("${jwt.token-prefix}")
    private String TOKEN_PREFIX;

    private final ObjectMapper objectMapper;

    public String generateToken(User user) {
        try {
            ZonedDateTime expiredTime = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)).plusSeconds(EXPIRATION_TIME);

            PrivateKey privateKey = KeyUtils.getPrivateKeyFromString("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDNo40K76Ac+mD47qJfNQhyzf2AR90N/VRXEFnap2P/Jn5nOhfkrzOwrAGNbL1bHgeHJ+PcuuAbyoyZFP82hRTzT/boXywYrHEEHtrde7Tt3AsItOZo4iT+Qt3q0yqQ9ViwvpGm2oy2ILghbB9iMJc3CDNzfSrm8VuU+Y9eXnyshJbjGuGw00kxBDzkDQ0QAPzg91kwMccmONY5IhnNCArSzIgSDJTCxennoXeHM5QDT8/uTVtWl5D14ifyjeD7jI7pjGYo7rZZugrngxXLrqX16IG3oxI8yn3U7b73FdSJdzRiSuUHau/DvRMqP9d/I1ySAa7NhOmyT9k0RGID++ITAgMBAAECggEAFzefVbFsWxk3GE6ebTAS/h3yayix9UG5jgLol4lNVEzHpLv9tUdwB0MZrHAEiQ8WO9TtzcluQspW9B8CGZO4+SekHDmdREPj7AUtIiiKFaOXE1U+q8TXTGXG9xups1LKwAR5dA86J9irMWArGEOdhGHGqAQPyKH40b8YyBzUPV30bsE5r6vr/MSOo5RG5SmM7cPcrjfnJfZoQUyzBdyOuQD9lqjEw/+5vrW/UqbTKhdhzHKOY0Ue5f6xowPAUnF6eomGeXkTXfGuo8BS1DO0TfMCWTjOxydDMg/00+GOMn7r6nGJedkavXwv8mQiyWWOhhETFSowl0ElNNp2b/kYYQKBgQDciIWnW4H6q41cengwzE/epxPu9fOo1zj5QURvsKzqGqA9v5fpgjott5zPA4aBDIRU36Z5sxQrskZfdCoYi5tG1fRCIV1D6NML2x+NSGnOHj4m2d5lAIOu0HdJsrhwpUfxu6OpTgtXw5ln1hxMd869v7v0XhwVyB0fZl19IAeYawKBgQDutdFATJbn8yqY/38MJNyGBRaDCvZE8oYMckkjC4KGouq3/GMoys3q+L+lbtHwQ/Ov4d7DLPm2wsH3/rMC+EmW0gUckWvGkgigzP7bXh2XgK/+39+SFtFBspZ2+sAs7OM5MXSEDfq1pFS/myFnRVFZD/Zj+pBHE0NizXmi0Rpm+QKBgAV0ZwHltIhTt/JioRhr5UnXjhmqAbXaZPFdWOj+ULiVogME+yfubav1z64bIzeL7LYAtihqcoRGK5Fu2R+0TEnrPthn5NuqJZY0fEpNUTik7NqfJu4OnjMzus+a2Q8IN4hWIM0uPf+UufWkiRynFfkBhg+cSEuNoJLHQGa45RczAoGBAOQKraxPfpp3ouomqqhfTox5r9fgUQPs7EJXq6bfCAK3AKRjaBdAJxeiKvNPux9cyx/rF3Q9MxF1XPgdScY8i5CZcmioQ92fBL8ZFx9Z+csSFQ/Xry1QvveUNEnVomFIrImmxTiiYi/EnXGuMWsGvX/nz+oTJGHB9U4eHWV9fUuBAoGBANR2qxYRqmYxBHgozAkC1hnvQZq7mQ3fPu2biLFXK0GwvmGWbY7bIBl/t2yrg/CR4fbGFnFCeL7J3fff7puihefMWlvI9QGhUBCfV4qoZ3nS1sHnxPgUsXe9KV8yeLhz7sYhHwGxr4b/H823zjZUktVU10HQyiHmKUi/kEL7ovFi");

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
