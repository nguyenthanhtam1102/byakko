package com.byakko.service.authentication.domain.domainapplication.handler.shopowner;

import com.byakko.common.domain.domainapplication.MessageManager;
import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.ShopOwnerChangePasswordCommand;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.ShopOwnerRepository;
import com.byakko.service.authentication.domain.domainapplication.utils.PasswordUtils;
import com.byakko.service.authentication.domain.domaincore.entity.ShopOwner;
import org.springframework.stereotype.Component;

@Component
public class ShopOwnerChangePasswordCommandHandler {

    private final ShopOwnerRepository shopOwnerRepository;
    private final PasswordUtils passwordUtils;
    private final MessageManager messageManager;

    public ShopOwnerChangePasswordCommandHandler(ShopOwnerRepository shopOwnerRepository, PasswordUtils passwordUtils, MessageManager messageManager) {
        this.shopOwnerRepository = shopOwnerRepository;
        this.passwordUtils = passwordUtils;
        this.messageManager = messageManager;
    }
    public void changePassword(ShopOwnerChangePasswordCommand command) {
        ShopOwner shopOwner = shopOwnerRepository.findById(command.getId())
                .orElseThrow(()->new RuntimeException("Shop owner not found"));

        if(!passwordUtils.matches(command.getCurrentPassword(), shopOwner.getPassword()))
            throw new RuntimeException("Mật khẩu hiện tại không chính xác");

        shopOwner.setPassword(passwordUtils.encode(command.getNewPassword()));

        shopOwner.validate();
        shopOwnerRepository.save(shopOwner);
    }

}
