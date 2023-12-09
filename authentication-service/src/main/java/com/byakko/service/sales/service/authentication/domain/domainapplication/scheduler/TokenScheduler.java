package com.byakko.service.sales.service.authentication.domain.domainapplication.scheduler;

import com.byakko.service.sales.service.authentication.domain.domainapplication.port.output.repository.TokenRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TokenScheduler {

    private final TokenRepository tokenRepository;

    public TokenScheduler(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void resetTokenProcessing() {
        tokenRepository.deleteAll();
    }

}
