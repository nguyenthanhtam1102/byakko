package com.byakko.service.authentication.dataaccess.adapter;

import com.byakko.service.authentication.dataaccess.mapper.ShopOwnerMapper;
import com.byakko.service.authentication.dataaccess.repository.ShopOwnerJpaRepository;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.ShopOwnerRepository;
import com.byakko.service.authentication.domain.domaincore.entity.ShopOwner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ShopOwnerRepositoryImpl implements ShopOwnerRepository {

    private final ShopOwnerJpaRepository shopOwnerJpaRepository;

    @Override
    public Optional<ShopOwner> findById(String id) {
        return shopOwnerJpaRepository.findById(id).map(ShopOwnerMapper::toShopOwner);
    }

    @Override
    public ShopOwner save(ShopOwner shopOwner) {
        return ShopOwnerMapper.toShopOwner(shopOwnerJpaRepository.save(ShopOwnerMapper.toShopOwnerEntity(shopOwner)));
    }

}
