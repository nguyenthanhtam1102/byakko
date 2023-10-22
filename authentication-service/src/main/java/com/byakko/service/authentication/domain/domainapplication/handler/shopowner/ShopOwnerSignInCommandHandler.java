package com.byakko.service.authentication.domain.domainapplication.handler.shopowner;

import com.byakko.common.DomainConstants;
import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.service.authentication.application.security.JwtProvider;
import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.ShopOwnerSignInCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.ShopOwnerSignInResponse;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.ShopOwnerRepository;
import com.byakko.service.authentication.domain.domainapplication.utils.PasswordUtils;
import com.byakko.service.authentication.domain.domaincore.entity.ShopOwner;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class ShopOwnerSignInCommandHandler {

    private final ShopOwnerRepository shopOwnerRepository;
    private final PasswordUtils passwordUtils;
    private final JwtProvider jwtProvider;

    public ShopOwnerSignInCommandHandler(ShopOwnerRepository shopOwnerRepository, PasswordUtils passwordUtils, JwtProvider jwtProvider) {
        this.shopOwnerRepository = shopOwnerRepository;
        this.passwordUtils = passwordUtils;
        this.jwtProvider = jwtProvider;
    }

    public ShopOwnerSignInResponse signIn(ShopOwnerSignInCommand command) {
        ShopOwner shopOwner = shopOwnerRepository.findById(command.getUsername())
                .orElseThrow(() -> new NotFoundException("account not found"));

        if(!passwordUtils.matches(command.getPassword(), shopOwner.getUsername()))
            throw new RuntimeException("Password not correct");

        return ShopOwnerSignInResponse.Builder.builder()
                .idToken(jwtProvider.generateToken(shopOwner))
                .refreshToken("")
                .expiresTime(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)).plusMinutes(5).toEpochSecond())
                .userId(shopOwner.getId().getValue())
                .build();
    }
}
