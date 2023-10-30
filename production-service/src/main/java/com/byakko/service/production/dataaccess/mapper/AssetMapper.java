package com.byakko.service.production.dataaccess.mapper;

import com.byakko.service.production.dataaccess.entity.AssetEntity;
import com.byakko.service.production.domain.domainapplication.dto.asset.AssetResponse;
import com.byakko.service.production.domain.domaincore.entity.Asset;
import com.byakko.service.production.domain.domaincore.valueobject.AssetId;

public class AssetMapper {

    public static Asset toAsset(AssetEntity assetEntity) {
        return Asset.Builder.builder()
                .id(new AssetId(assetEntity.getId()))
                .filename(assetEntity.getFilename())
                .filetype(assetEntity.getFiletype())
                .size(assetEntity.getSize())
                .url(assetEntity.getUrl())
                .slug(assetEntity.getSlug())
                .altText(assetEntity.getAltText())
//                .createdAt(assetEntity.getCreatedAt())
                .build();
    }

    public static AssetResponse toAssetResponse(AssetEntity asset) {
        return AssetResponse.Builder.builder()
                .id(asset.getId())
                .filename(asset.getFilename())
                .filetype(asset.getFiletype())
                .size(asset.getSize())
                .url(asset.getUrl())
                .slug(asset.getSlug())
                .altText(asset.getAltText())
                .isGraphic(asset.isGraphic())
                .graphic(asset.isGraphic() ? new AssetResponse.Graphic(asset.getWidth(), asset.getHeight()) : null)
                .createdAt(asset.getCreatedAt().toEpochSecond())
                .build();
    }

}
