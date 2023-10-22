package com.byakko.service.authentication.domain.domainapplication.port.output.repository;

import com.byakko.service.authentication.domain.domaincore.valueobject.Token;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface TokenRepository {

    Optional<Token> findByHashedData(String hashedData);
    int countTokenByUserId(String userId);
    Token save(Token token);
    void delete(Token token);

}
