package com.byakko.service.authentication.domain.domainapplication.port.input.service;

import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.ShopOwnerSignInCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.ShopOwnerSignInResponse;
import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.ShopOwnerSignUpCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.ShopOwnerSignUpResponse;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
public interface ShopOwnerService {

    ShopOwnerSignInResponse signIn(@Validated ShopOwnerSignInCommand command);
    ShopOwnerSignUpResponse signUp(@Validated ShopOwnerSignUpCommand command);

}
