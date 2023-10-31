package com.byakko.service.authentication.domain.domainapplication.handler.shopowner;

import com.byakko.service.authentication.domain.domainapplication.dto.customer.DeleteCustomerCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.shopowner.DeleteShopOwner;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.ShopOwnerRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import com.byakko.service.authentication.domain.domaincore.entity.ShopOwner;
import com.byakko.service.authentication.domain.domaincore.valueobject.CustomerStatus;
import com.byakko.service.authentication.domain.domaincore.valueobject.ShopOwnerStatus;
import org.springframework.stereotype.Component;

@Component
public class ShopOwnerDeleteCommandHandler {
    private final ShopOwnerRepository shopOwnerRepository;

    public ShopOwnerDeleteCommandHandler(ShopOwnerRepository shopOwnerRepository) {
        this.shopOwnerRepository = shopOwnerRepository;
    }


    public void delete(DeleteShopOwner command) {
        ShopOwner shopOwner = shopOwnerRepository.findById(command.getId())
                .orElseThrow(()->new RuntimeException("Shop Owner not found"));

        shopOwner.setStatus(ShopOwnerStatus.DELETED);
        shopOwner.validate();

        shopOwnerRepository.save(shopOwner);
    }
}
