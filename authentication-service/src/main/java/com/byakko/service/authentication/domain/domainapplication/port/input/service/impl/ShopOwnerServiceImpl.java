package com.byakko.service.authentication.domain.domainapplication.port.input.service.impl;

import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.ShopOwnerSignInCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.ShopOwnerSignInResponse;
import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.ShopOwnerSignUpCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.ShopOwnerSignUpResponse;
import com.byakko.service.authentication.domain.domainapplication.handler.shopowner.ShopOwnerSignInCommandHandler;
import com.byakko.service.authentication.domain.domainapplication.handler.shopowner.ShopOwnerSignUpCommandHandler;
import com.byakko.service.authentication.domain.domainapplication.port.input.service.ShopOwnerService;
import org.springframework.stereotype.Component;

@Component
public class ShopOwnerServiceImpl implements ShopOwnerService {

    private final ShopOwnerSignInCommandHandler shopOwnerSignInCommandHandler;
    private final ShopOwnerSignUpCommandHandler shopOwnerSignUpCommandHandler;

    public ShopOwnerServiceImpl(ShopOwnerSignInCommandHandler shopOwnerSignInCommandHandler,
                                ShopOwnerSignUpCommandHandler shopOwnerSignUpCommandHandler) {
        this.shopOwnerSignInCommandHandler = shopOwnerSignInCommandHandler;
        this.shopOwnerSignUpCommandHandler = shopOwnerSignUpCommandHandler;
    }

    @Override
    public ShopOwnerSignInResponse signIn(ShopOwnerSignInCommand command) {
        return shopOwnerSignInCommandHandler.signIn(command);
    }

    @Override
    public ShopOwnerSignUpResponse signUp(ShopOwnerSignUpCommand command) {
        return shopOwnerSignUpCommandHandler.signUp(command);
    }
}
