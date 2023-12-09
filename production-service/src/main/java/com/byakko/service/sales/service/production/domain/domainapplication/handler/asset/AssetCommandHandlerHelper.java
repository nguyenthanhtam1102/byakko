package com.byakko.service.sales.service.production.domain.domainapplication.handler.asset;

import com.byakko.service.sales.common.domain.exception.NotFoundException;
import com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository.AssetRepository;
import com.byakko.service.sales.service.production.domain.domaincore.entity.Asset;
import org.springframework.stereotype.Component;

@Component
public class AssetCommandHandlerHelper {

    private final AssetRepository assetRepository;

    public AssetCommandHandlerHelper(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public Asset findById(String id) {
        return assetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Asset %s not found", id)));
    }

}
