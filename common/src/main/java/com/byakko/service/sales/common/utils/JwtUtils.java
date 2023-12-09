package com.byakko.service.sales.common.utils;

import com.byakko.service.sales.common.domain.exception.DomainException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.MalformedParametersException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Slf4j
@Component
public class JwtUtils {

    @Value("${jwt.token-prefix}")
    private String TOKEN_PREFIX;

    @Autowired
    private ObjectMapper objectMapper;

    public void validateToken(String token) {
        try {
            PublicKey publicKey = KeyUtils.getPublicKeyFromString("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzaONCu+gHPpg+O6iXzUIcs39gEfdDf1UVxBZ2qdj/yZ+ZzoX5K8zsKwBjWy9Wx4Hhyfj3LrgG8qMmRT/NoUU80/26F8sGKxxBB7a3Xu07dwLCLTmaOIk/kLd6tMqkPVYsL6RptqMtiC4IWwfYjCXNwgzc30q5vFblPmPXl58rISW4xrhsNNJMQQ85A0NEAD84PdZMDHHJjjWOSIZzQgK0syIEgyUwsXp56F3hzOUA0/P7k1bVpeQ9eIn8o3g+4yO6YxmKO62WboK54MVy66l9eiBt6MSPMp91O2+9xXUiXc0YkrlB2rvw70TKj/XfyNckgGuzYTpsk/ZNERiA/viEwIDAQAB");
            String jwt = token.replace(TOKEN_PREFIX, "").replace("%20", "").trim();
            Jwts.parser().setSigningKey(publicKey).parseClaimsJws(jwt);
        } catch (MalformedParametersException e) {
            throw new RuntimeException("Invalid jwt token");
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token đã hết hạn");
        } catch (SignatureException e) {

        } catch (UnsupportedJwtException e) {
            throw new DomainException("Không hổ trợ jwt token");
        } catch (IllegalArgumentException e) {
            throw new DomainException("Jwt claims string is empty");
        }
    }

    public JwtPayload getTokenPayload(String token) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDNo40K76Ac+mD47qJfNQhyzf2AR90N/VRXEFnap2P/Jn5nOhfkrzOwrAGNbL1bHgeHJ+PcuuAbyoyZFP82hRTzT/boXywYrHEEHtrde7Tt3AsItOZo4iT+Qt3q0yqQ9ViwvpGm2oy2ILghbB9iMJc3CDNzfSrm8VuU+Y9eXnyshJbjGuGw00kxBDzkDQ0QAPzg91kwMccmONY5IhnNCArSzIgSDJTCxennoXeHM5QDT8/uTVtWl5D14ifyjeD7jI7pjGYo7rZZugrngxXLrqX16IG3oxI8yn3U7b73FdSJdzRiSuUHau/DvRMqP9d/I1ySAa7NhOmyT9k0RGID++ITAgMBAAECggEAFzefVbFsWxk3GE6ebTAS/h3yayix9UG5jgLol4lNVEzHpLv9tUdwB0MZrHAEiQ8WO9TtzcluQspW9B8CGZO4+SekHDmdREPj7AUtIiiKFaOXE1U+q8TXTGXG9xups1LKwAR5dA86J9irMWArGEOdhGHGqAQPyKH40b8YyBzUPV30bsE5r6vr/MSOo5RG5SmM7cPcrjfnJfZoQUyzBdyOuQD9lqjEw/+5vrW/UqbTKhdhzHKOY0Ue5f6xowPAUnF6eomGeXkTXfGuo8BS1DO0TfMCWTjOxydDMg/00+GOMn7r6nGJedkavXwv8mQiyWWOhhETFSowl0ElNNp2b/kYYQKBgQDciIWnW4H6q41cengwzE/epxPu9fOo1zj5QURvsKzqGqA9v5fpgjott5zPA4aBDIRU36Z5sxQrskZfdCoYi5tG1fRCIV1D6NML2x+NSGnOHj4m2d5lAIOu0HdJsrhwpUfxu6OpTgtXw5ln1hxMd869v7v0XhwVyB0fZl19IAeYawKBgQDutdFATJbn8yqY/38MJNyGBRaDCvZE8oYMckkjC4KGouq3/GMoys3q+L+lbtHwQ/Ov4d7DLPm2wsH3/rMC+EmW0gUckWvGkgigzP7bXh2XgK/+39+SFtFBspZ2+sAs7OM5MXSEDfq1pFS/myFnRVFZD/Zj+pBHE0NizXmi0Rpm+QKBgAV0ZwHltIhTt/JioRhr5UnXjhmqAbXaZPFdWOj+ULiVogME+yfubav1z64bIzeL7LYAtihqcoRGK5Fu2R+0TEnrPthn5NuqJZY0fEpNUTik7NqfJu4OnjMzus+a2Q8IN4hWIM0uPf+UufWkiRynFfkBhg+cSEuNoJLHQGa45RczAoGBAOQKraxPfpp3ouomqqhfTox5r9fgUQPs7EJXq6bfCAK3AKRjaBdAJxeiKvNPux9cyx/rF3Q9MxF1XPgdScY8i5CZcmioQ92fBL8ZFx9Z+csSFQ/Xry1QvveUNEnVomFIrImmxTiiYi/EnXGuMWsGvX/nz+oTJGHB9U4eHWV9fUuBAoGBANR2qxYRqmYxBHgozAkC1hnvQZq7mQ3fPu2biLFXK0GwvmGWbY7bIBl/t2yrg/CR4fbGFnFCeL7J3fff7puihefMWlvI9QGhUBCfV4qoZ3nS1sHnxPgUsXe9KV8yeLhz7sYhHwGxr4b/H823zjZUktVU10HQyiHmKUi/kEL7ovFi");

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey key = keyFactory.generatePrivate(keySpec);

            String payloadString = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "").trim())
                    .getBody()
                    .getSubject();

            return objectMapper.readValue(payloadString, JwtPayload.class);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
