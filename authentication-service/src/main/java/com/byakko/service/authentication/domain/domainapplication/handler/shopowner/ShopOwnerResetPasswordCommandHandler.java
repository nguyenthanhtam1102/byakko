package com.byakko.service.authentication.domain.domainapplication.handler.shopowner;

import com.byakko.service.authentication.domain.domainapplication.dto.customer.CustomerResetPasswordCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.ShopOwnerResetPasswordCommand;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.ShopOwnerRepository;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.TokenRepository;
import com.byakko.service.authentication.domain.domainapplication.utils.PasswordUtils;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import com.byakko.service.authentication.domain.domaincore.entity.ShopOwner;
import com.byakko.service.authentication.domain.domaincore.valueobject.Token;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
@Component
public class ShopOwnerResetPasswordCommandHandler {
    private final ShopOwnerRepository shopOwnerRepository;
    private final TokenRepository tokenRepository;
    private final PasswordUtils passwordUtils;

    public ShopOwnerResetPasswordCommandHandler(ShopOwnerRepository shopOwnerRepository, TokenRepository tokenRepository, PasswordUtils passwordUtils) {
        this.shopOwnerRepository = shopOwnerRepository;
        this.tokenRepository = tokenRepository;
        this.passwordUtils = passwordUtils;
    }


    @Transactional
    public void handler(ShopOwnerResetPasswordCommand command) {
        Token token = tokenRepository.findByHashedData(command.getToken())
                .orElseThrow(() -> new RuntimeException("OTP not found"));
        token.validate();
        ShopOwner shopOwner = shopOwnerRepository.findById(token.getUserId())
                .orElseThrow(()->new RuntimeException("Shop Owner Not Found"));
        token.setUsed(Boolean.TRUE);
        tokenRepository.save(token);
        shopOwner.setPassword(passwordUtils.encode(command.getNewPassword()));
        shopOwner.validate();
        shopOwnerRepository.save(shopOwner);
    }
}
