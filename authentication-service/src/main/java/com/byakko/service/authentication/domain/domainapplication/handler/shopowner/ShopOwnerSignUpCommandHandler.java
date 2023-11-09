package com.byakko.service.authentication.domain.domainapplication.handler.shopowner;

import com.byakko.common.DomainConstants;
import com.byakko.service.authentication.dataaccess.entity.MenuEntity;
import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.ShopOwnerSignUpCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.ShopOwnerSignUpResponse;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.MenuRepository;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.ShopOwnerRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Menu;
import com.byakko.service.authentication.domain.domaincore.entity.ShopOwner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class ShopOwnerSignUpCommandHandler {

    private final ShopOwnerRepository shopOwnerRepository;
    private final MenuRepository menuRepository;
    private final PasswordEncoder passwordEncoder;
    public ShopOwnerSignUpCommandHandler(ShopOwnerRepository shopOwnerRepository, MenuRepository menuRepository, PasswordEncoder passwordEncoder) {
        this.shopOwnerRepository = shopOwnerRepository;
        this.menuRepository = menuRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ShopOwnerSignUpResponse signUp(ShopOwnerSignUpCommand command) {
        if(shopOwnerRepository.findByPhoneOrEmail(command.getPhone(), command.getEmail()).isPresent())
            throw new RuntimeException("Email and Phone is exists");
        Menu menu = Menu.Builder.builder()
                        .name(command.getUsername())
                        .build();
        menu.initialize();
        Menu menu1 = menuRepository.save(menu);
        ShopOwner shopOwner = ShopOwner.Builder.builder()
                .username(command.getUsername())
                .menu(menu1)
                .phone(command.getPhone())
                .email(command.getEmail())
                .password(command.getPassword())
                .build();
        shopOwner.validatePassword(shopOwner.getPassword());
        shopOwner.setPassword(passwordEncoder.encode(command.getPassword()));
        shopOwner.initialize();
        shopOwner.validate();
        shopOwnerRepository.save(shopOwner);
        ShopOwnerSignUpResponse response = new ShopOwnerSignUpResponse();
                response.setUserId(shopOwner.getId().getValue());
                response.setUsername(shopOwner.getUsername());
                response.setCreatedAt(shopOwner.getCreatedAt().toEpochSecond());
        return response;
    }

}
