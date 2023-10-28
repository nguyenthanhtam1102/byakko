package com.byakko.service.production.domain.domainapplication.mapper;

import com.byakko.service.production.domain.domainapplication.dto.asset.AssetResponse;
import com.byakko.service.production.domain.domaincore.entity.Asset;

public class AssetMapper {

    public static AssetResponse toAssetResponse(Asset asset) {
        return AssetResponse.Builder.builder()
                .id(asset.getId().getValue())
                .filename(asset.getFilename())
                .filetype(asset.getFiletype())
                .size(asset.getSize())
                .url(asset.getUrl())
                .slug(asset.getSlug())
                .altText(asset.getAltText())
                .isGraphic(asset.isGraphic())
                .graphic(asset.isGraphic() ? new AssetResponse.Graphic(asset.getWidth(), asset.getHeight()) : null)
//                .createdAt(asset.getCreatedAt().toEpochSecond())
                .build();
    }

}
