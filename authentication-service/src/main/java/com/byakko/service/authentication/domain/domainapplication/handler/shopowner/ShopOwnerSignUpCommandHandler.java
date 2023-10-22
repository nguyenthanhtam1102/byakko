package com.byakko.service.authentication.domain.domainapplication.handler.shopowner;

import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.ShopOwnerSignUpCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.ShopOwnerSignUpResponse;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.ShopOwnerRepository;
import com.byakko.service.authentication.domain.domaincore.entity.ShopOwner;
import org.springframework.stereotype.Component;

@Component
public class ShopOwnerSignUpCommandHandler {

    private final ShopOwnerRepository shopOwnerRepository;

    public ShopOwnerSignUpCommandHandler(ShopOwnerRepository shopOwnerRepository) {
        this.shopOwnerRepository = shopOwnerRepository;
    }

    public ShopOwnerSignUpResponse signUp(ShopOwnerSignUpCommand command) {
        if(shopOwnerRepository.findById(command.getUsername()).isPresent())
            throw new RuntimeException("Username is exists");

        ShopOwner shopOwner = ShopOwner.Builder.builder()
                .username(command.getUsername())
                .password(command.getPassword())
                .build();
        shopOwner.initialize();

        shopOwner.validate();
        shopOwnerRepository.save(shopOwner);

        return ShopOwnerSignUpResponse.Builder.builder()
                .userId(shopOwner.getId().getValue())
                .username(shopOwner.getUsername())
                .createdAt(shopOwner.getCreatedAt().toEpochSecond())
                .build();
    }

}
