package com.byakko.service.sales.service.production.domain.domainapplication.handler.asset;

import com.byakko.service.sales.service.production.domain.domainapplication.dto.asset.DeleteAssetCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository.AssetRepository;
import com.byakko.service.sales.service.production.domain.domaincore.entity.Asset;
import org.springframework.stereotype.Component;

@Component
public class DeleteAssetCommandHandler {

    private final AssetRepository assetRepository;
    private final AssetCommandHandlerHelper assetCommandHandlerHelper;

    public DeleteAssetCommandHandler(AssetRepository assetRepository,
                                     AssetCommandHandlerHelper assetCommandHandlerHelper) {
        this.assetRepository = assetRepository;
        this.assetCommandHandlerHelper = assetCommandHandlerHelper;
    }

    public void handler(DeleteAssetCommand command) {
        Asset asset = assetCommandHandlerHelper.findById(command.getId());
        assetRepository.delete(asset);
    }

}
