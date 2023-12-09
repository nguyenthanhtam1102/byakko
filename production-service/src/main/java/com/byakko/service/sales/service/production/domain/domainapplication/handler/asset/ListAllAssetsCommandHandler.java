package com.byakko.service.sales.service.production.domain.domainapplication.handler.asset;

import com.byakko.service.sales.common.application.dto.ListAllResponse;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.asset.ListAllAssetCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.asset.ListAllAssetResponse;
import com.byakko.service.sales.service.production.domain.domainapplication.mapper.AssetMapper;
import com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository.AssetRepository;
import com.byakko.service.sales.service.production.domain.domaincore.entity.Asset;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ListAllAssetsCommandHandler {

    private final AssetRepository assetRepository;

    public ListAllAssetsCommandHandler(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public ListAllAssetResponse handler(ListAllAssetCommand command) {
        Page<Asset> page = assetRepository.findAll(command.getPage(), command.getLimit(), command.getQuery());
        return ListAllAssetResponse.builder()
                .data(page.get().map(AssetMapper::toAssetResponse).toList())
                .pagination(ListAllResponse.Pagination.toPagination(page))
                .build();
    }

}
