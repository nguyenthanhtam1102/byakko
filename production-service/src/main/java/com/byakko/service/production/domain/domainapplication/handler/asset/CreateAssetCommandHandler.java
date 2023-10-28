package com.byakko.service.production.domain.domainapplication.handler.asset;

import com.byakko.service.production.domain.domainapplication.dto.asset.AssetResponse;
import com.byakko.service.production.domain.domainapplication.dto.asset.CreateAssetCommand;
import com.byakko.service.production.domain.domainapplication.mapper.AssetMapper;
import com.byakko.service.production.domain.domainapplication.port.output.repository.AssetRepository;
import com.byakko.service.production.domain.domaincore.entity.Asset;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateAssetCommandHandler {

    private final AssetRepository assetRepository;

    public CreateAssetCommandHandler(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Transactional
    public AssetResponse handler(CreateAssetCommand command) {
        Asset asset = Asset.Builder.builder()
                .filename(command.getFilename())
                .url(command.getUrl())
                .contents(command.getContents())
                .slug(command.getSlug())
                .altText(command.getAltText())
                .build();

        asset.initialize();
        asset.validate();

        asset = assetRepository.save(asset);

        return AssetMapper.toAssetResponse(asset);
    }

}
