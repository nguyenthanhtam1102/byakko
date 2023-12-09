package com.byakko.service.sales.service.production.domain.domainapplication.handler.asset;

import com.byakko.service.sales.service.production.domain.domainapplication.dto.asset.AssetResponse;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.asset.GetAssetCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.mapper.AssetMapper;
import com.byakko.service.sales.service.production.domain.domaincore.entity.Asset;
import org.springframework.stereotype.Component;

@Component
public class GetAssetCommandHandler {

    private final AssetCommandHandlerHelper assetCommandHandlerHelper;

    public GetAssetCommandHandler(AssetCommandHandlerHelper assetCommandHandlerHelper) {
        this.assetCommandHandlerHelper = assetCommandHandlerHelper;
    }

    public AssetResponse handler(GetAssetCommand command) {
        Asset asset = assetCommandHandlerHelper.findById(command.getId());
        return AssetMapper.toAssetResponse(asset);
    }

}
