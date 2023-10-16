package com.byakko.common.utils;

import com.byakko.service.authentication.application.security.KeyUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.MalformedParametersException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Component
public class JwtUtilsImpl implements JwtUtils {

    @Value("${jwt.token-prefix}")
    private String TOKEN_PREFIX;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void validateToken(String token) {
        try {
            PublicKey publicKey = KeyUtils.getPublicKeyFromString("");
            Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token.replace(TOKEN_PREFIX, "").trim());
        } catch (MalformedParametersException e) {
            log.error("Invalid JWT Token");
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT Token");
        } catch (SignatureException e) {

        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT Token");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims String is Empty");
        }
    }

    @Override
    public JwtPayload getTokenPayload(String token) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode("private key string");
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
