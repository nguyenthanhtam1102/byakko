package com.byakko.service.authentication.application.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

@Service
public class JwtHelper {
    @Value("{jwt.secret.key}")
    private String secret;
    public static SecretKey generateHmacSha256Key() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
        return keyGenerator.generateKey();
    }
    public String generateToken(String data) {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
        Key key = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());

        String token = Jwts.builder()
                .setSubject(data)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
        return token;
    }

    public Claims decodeToken(String token){
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
        Key key = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());

        Claims claims =  Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
