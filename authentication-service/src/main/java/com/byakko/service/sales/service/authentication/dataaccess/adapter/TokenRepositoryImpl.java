package com.byakko.service.sales.service.authentication.dataaccess.adapter;

import com.byakko.service.sales.service.authentication.dataaccess.mapper.TokenMapper;
import com.byakko.service.sales.service.authentication.dataaccess.repository.TokenJpaRepository;
import com.byakko.service.sales.service.authentication.domain.domainapplication.port.output.repository.TokenRepository;
import com.byakko.service.sales.service.authentication.domain.domaincore.valueobject.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TokenRepositoryImpl implements TokenRepository {

    private final TokenJpaRepository tokenJpaRepository;

    @Override
    public Optional<Token> findByHashedData(String hashedData) {
        return tokenJpaRepository.findByHashedData(hashedData).map(TokenMapper::toOTP);
    }

    @Override
    public int countTokenByUserId(String userId) {
        return tokenJpaRepository.countByUserId(userId);
    }

    @Override
    public Token save(Token token) {
        return TokenMapper.toOTP(tokenJpaRepository.save(TokenMapper.toOTPEntity(token)));
    }

    @Override
    public void delete(Token token) {
        tokenJpaRepository.delete(TokenMapper.toOTPEntity(token));
    }

    @Override
    public void deleteAll() {
        tokenJpaRepository.deleteAll();
    }

}
