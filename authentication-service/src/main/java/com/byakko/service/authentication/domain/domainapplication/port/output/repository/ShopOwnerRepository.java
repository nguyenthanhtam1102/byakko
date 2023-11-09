package com.byakko.service.authentication.domain.domainapplication.port.output.repository;

import com.byakko.service.authentication.domain.domaincore.entity.Menu;
import com.byakko.service.authentication.domain.domaincore.entity.ShopOwner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ShopOwnerRepository {

    Optional<ShopOwner> findById(String id);
    ShopOwner save(ShopOwner shopOwner);
    Optional<ShopOwner> findByPhoneOrEmail(String phone,String email);
}
