package com.byakko.service.authentication.domain.domainapplication.handler.customer;

import com.byakko.common.DomainConstants;
import com.byakko.common.domain.exception.DomainException;
import com.byakko.service.authentication.domain.domainapplication.dto.customer.SendResetPasswordMailCommand;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.TokenRepository;
import com.byakko.service.authentication.domain.domainapplication.utils.MailSenderHelper;
import com.byakko.service.authentication.domain.domainapplication.utils.MailTemplate;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import com.byakko.service.authentication.domain.domaincore.valueobject.Token;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.UUID;

@Component
public class SendResetPasswordMailCommandHandler {

    private final CustomerCommandHandlerHelper customerCommandHandlerHelper;
    private final TokenRepository tokenRepository;
    private final MailSenderHelper mailSenderHelper;
    private final MailTemplate mailTemplate;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;

    @Value("${maxtoken}")
    private int MAX_TOKEN;

    public SendResetPasswordMailCommandHandler(CustomerCommandHandlerHelper customerCommandHandlerHelper,
                                               TokenRepository tokenRepository,
                                               MailSenderHelper mailSenderHelper,
                                               MailTemplate mailTemplate, ObjectMapper objectMapper, PasswordEncoder passwordEncoder) {
        this.customerCommandHandlerHelper = customerCommandHandlerHelper;
        this.tokenRepository = tokenRepository;
        this.mailSenderHelper = mailSenderHelper;
        this.mailTemplate = mailTemplate;
        this.objectMapper = objectMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void handler(SendResetPasswordMailCommand command) {
        Customer customer = customerCommandHandlerHelper.findCustomerByPhoneOrEmail(command.getPhoneOrEmail(), command.getPhoneOrEmail());

        if(tokenRepository.countTokenByUserId(customer.getId().getValue()) > MAX_TOKEN)
            throw new DomainException("Bạn đã vượt quá số lượt cho phép");

        Token token = Token.Builder.builder()
                    .userId(customer.getId().getValue())
                    .randomData(UUID.randomUUID().toString())
                    .createdAt(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)))
                    .expiredTime(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)).plusSeconds(300))
                    .build();

        String rawToken;
        try {
            rawToken = objectMapper.writeValueAsString(token);
        } catch (Exception ex) {
            throw new RuntimeException("Không thể chuyển token sang rawToken");
        }
        token.setHashedData(passwordEncoder.encode(rawToken));

        tokenRepository.save(token);

        mailSenderHelper.send(mailTemplate.getPasswordResetMailTemplate(customer.getEmail(), token.getHashedData()));
    }

}
