package com.byakko.service.sales.service.authentication.domain.domainapplication.handler.shopowner;

import com.byakko.service.sales.common.domain.exception.NotFoundException;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.customer.VerifyEmailAddressCommand;
import com.byakko.service.sales.service.authentication.domain.domainapplication.port.output.repository.ShopOwnerRepository;
import com.byakko.service.sales.service.authentication.domain.domainapplication.port.output.repository.TokenRepository;
import com.byakko.service.sales.service.authentication.domain.domaincore.entity.ShopOwner;
import com.byakko.service.sales.service.authentication.domain.domaincore.valueobject.Token;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component
public class ShopOwnerVerifyEmailAddressCommandHandler {

    private final ShopOwnerRepository shopOwnerRepository;
    private final TokenRepository tokenRepository;

    public ShopOwnerVerifyEmailAddressCommandHandler(ShopOwnerRepository shopOwnerRepository, TokenRepository tokenRepository) {
        this.shopOwnerRepository = shopOwnerRepository;
        this.tokenRepository = tokenRepository;
    }


    @Transactional
    public void verify(VerifyEmailAddressCommand command) {
        Token token = tokenRepository.findByHashedData(command.getToken())
                .orElseThrow(() -> new RuntimeException("OTP not found"));

        token.validate();

        ShopOwner shopOwner = shopOwnerRepository.findById(token.getUserId())
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        token.setUsed(Boolean.TRUE);
        tokenRepository.save(token);

        shopOwner.setVerified(true);
        shopOwner.validate();

        shopOwnerRepository.save(shopOwner);
    }

}
