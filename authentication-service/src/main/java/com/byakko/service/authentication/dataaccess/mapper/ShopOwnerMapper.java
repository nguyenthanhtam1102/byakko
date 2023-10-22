package com.byakko.service.authentication.dataaccess.mapper;

import com.byakko.service.authentication.dataaccess.entity.ShopOwnerEntity;
import com.byakko.service.authentication.domain.domaincore.entity.ShopOwner;
import com.byakko.service.authentication.domain.domaincore.valueobject.UserId;

public class ShopOwnerMapper {

    public static ShopOwnerEntity toShopOwnerEntity(ShopOwner shopOwner) {
        return ShopOwnerEntity.builder()
                .id(shopOwner.getId().getValue())
                .password(shopOwner.getPassword())
                .createdAt(shopOwner.getCreatedAt())
                .updatedAt(shopOwner.getUpdatedAt())
                .status(shopOwner.getStatus())
                .build();
    }

    public static ShopOwner toShopOwner(ShopOwnerEntity shopOwnerEntity) {
        return ShopOwner.Builder.builder()
                .id(new UserId(shopOwnerEntity.getId()))
                .password(shopOwnerEntity.getPassword())
                .createdAt(shopOwnerEntity.getCreatedAt())
                .updatedAt(shopOwnerEntity.getUpdatedAt())
                .status(shopOwnerEntity.getStatus())
                .build();
    }

}
