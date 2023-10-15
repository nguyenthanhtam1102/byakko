package com.byakko.service.authentication.application.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.MalformedParametersException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

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

            String token = Jwts.builder()
                    .setSubject("")
                    .setIssuedAt(new Date())
                    .setExpiration(expirationDate)
                    .signWith(SignatureAlgorithm.RS256, privateKey)
                    .compact();

            return TOKEN_PREFIX + " " + token;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Object getInfoFromToken(String token) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode("private key string");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey key = keyFactory.generatePrivate(keySpec);

            Object info = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "").trim())
                    .getBody()
                    .getSubject();

            return info;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean validateToken(String token) {
        try {
            PublicKey publicKey = KeyUtils.getPublicKeyFromString("");
            Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token.replace(TOKEN_PREFIX, "").trim());
            return true;
        } catch (MalformedParametersException e) {
            log.error("Invalid JWT Token");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT Token");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT Token");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims String is Empty");
        }
        return false;
    }

}
