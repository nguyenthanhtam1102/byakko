package com.byakko.service.sales.service.product.mappers;

import com.byakko.service.sales.service.product.dtos.assets.AssetMinResponse;
import com.byakko.service.sales.service.product.dtos.assets.AssetResponse;
import com.byakko.service.sales.service.product.models.Asset;

public class AssetMapper {

    public static AssetResponse toAssetResponse(Asset asset) {
        return AssetResponse.builder()
                .id(asset.getId())
                .filename(asset.getFilename())
                .url(asset.getUrl())
                .size(asset.getSize())
                .contentType(asset.getContentType())
                .createdAt(asset.getCreatedAt().toEpochSecond())
                .updatedAt(asset.getUpdatedAt().toEpochSecond())
                .build();
    }

    public static AssetMinResponse toAssetMinResponse(Asset asset) {
        return AssetMinResponse.builder()
                .id(asset.getId())
                .filename(asset.getFilename())
                .url(asset.getUrl())
                .build();
    }

}
