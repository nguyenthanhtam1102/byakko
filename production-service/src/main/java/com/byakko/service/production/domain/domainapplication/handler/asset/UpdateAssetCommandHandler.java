package com.byakko.service.production.domain.domainapplication.handler.asset;

import com.byakko.service.production.domain.domainapplication.dto.asset.AssetResponse;
import com.byakko.service.production.domain.domainapplication.dto.asset.UpdateAssetCommand;
import com.byakko.service.production.domain.domainapplication.mapper.AssetMapper;
import com.byakko.service.production.domain.domainapplication.port.output.repository.AssetRepository;
import com.byakko.service.production.domain.domaincore.entity.Asset;
import org.springframework.stereotype.Component;

@Component
public class UpdateAssetCommandHandler {

    private final AssetCommandHandlerHelper assetCommandHandlerHelper;
    private final AssetRepository assetRepository;

    public UpdateAssetCommandHandler(AssetCommandHandlerHelper assetCommandHandlerHelper,
                                     AssetRepository assetRepository) {
        this.assetCommandHandlerHelper = assetCommandHandlerHelper;
        this.assetRepository = assetRepository;
    }

    public AssetResponse handler(UpdateAssetCommand command) {
        Asset asset = assetCommandHandlerHelper.findById(command.getId());

        if(command.getFilename() != null) {
            asset.setFilename(command.getFilename());
        }

        if(command.getContents() != null) {
            asset.setContents(command.getContents());
        }

        if(command.getUrl() != null) {
            asset.setUrl(command.getUrl());
        }

        if(command.getSlug() != null) {
            asset.setSlug(command.getSlug());
        }

        if(command.getAltText() != null) {
            asset.setAltText(command.getAltText());
        }

        asset.validate();
        assetRepository.save(asset);

        return AssetMapper.toAssetResponse(asset);
    }

}
