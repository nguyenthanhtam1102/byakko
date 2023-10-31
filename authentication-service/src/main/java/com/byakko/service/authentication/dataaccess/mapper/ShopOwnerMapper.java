package com.byakko.service.authentication.dataaccess.mapper;

import com.byakko.service.authentication.dataaccess.entity.MenuEntity;
import com.byakko.service.authentication.dataaccess.entity.ShopOwnerEntity;
import com.byakko.service.authentication.dataaccess.repository.MenuJpaRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Menu;
import com.byakko.service.authentication.domain.domaincore.entity.ShopOwner;
import com.byakko.service.authentication.domain.domaincore.valueobject.MenuId;
import com.byakko.service.authentication.domain.domaincore.valueobject.UserId;

public class ShopOwnerMapper {
    public static ShopOwnerEntity toShopOwnerEntity(ShopOwner shopOwner) {
        return ShopOwnerEntity.builder()
                .id(shopOwner.getId().getValue())
                .password(shopOwner.getPassword())
                .createdAt(shopOwner.getCreatedAt())
                .updatedAt(shopOwner.getUpdatedAt())
                .username(shopOwner.getUsername())
                .email(shopOwner.getEmail())
                .verified(shopOwner.isVerified())
                .menu(MenuEntity.builder()
                        .id(shopOwner.getMenuId().getId().getValue())
                        .name(shopOwner.getUsername())
                        .build())
                .phone(shopOwner.getPhone())
                .status(shopOwner.getStatus())
                .build();
    }

    public static ShopOwner toShopOwner(ShopOwnerEntity shopOwnerEntity) {
        return ShopOwner.Builder.builder()
                .id(new UserId(shopOwnerEntity.getId()))
                .password(shopOwnerEntity.getPassword())
                .createdAt(shopOwnerEntity.getCreatedAt())
                .updatedAt(shopOwnerEntity.getUpdatedAt())
                .menu(new Menu(new MenuId(shopOwnerEntity.getMenu().getId()),shopOwnerEntity.getUsername()))
                .username(shopOwnerEntity.getUsername())
                .email(shopOwnerEntity.getEmail())
                .verified(shopOwnerEntity.isVerified())
                .phone(shopOwnerEntity.getPhone())
                .status(shopOwnerEntity.getStatus())
                .build();
    }

}
