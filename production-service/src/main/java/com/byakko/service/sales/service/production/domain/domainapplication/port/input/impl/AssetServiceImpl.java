package com.byakko.service.sales.service.production.domain.domainapplication.port.input.impl;

import com.byakko.service.production.domain.domainapplication.dto.asset.*;
import com.byakko.service.production.domain.domainapplication.handler.asset.*;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.asset.*;
import com.byakko.service.sales.service.production.domain.domainapplication.handler.asset.*;
import com.byakko.service.sales.service.production.domain.domainapplication.port.input.AssetService;
import org.springframework.stereotype.Component;

@Component
public class AssetServiceImpl implements AssetService {

    private final ListAllAssetsCommandHandler listAllAssetsCommandHandler;
    private final GetAssetCommandHandler getAssetCommandHandler;
    private final CreateAssetCommandHandler createAssetCommandHandler;
    private final UpdateAssetCommandHandler updateAssetCommandHandler;
    private final DeleteAssetCommandHandler deleteAssetCommandHandler;

    public AssetServiceImpl(ListAllAssetsCommandHandler listAllAssetsCommandHandler,
                            GetAssetCommandHandler getAssetCommandHandler,
                            CreateAssetCommandHandler createAssetCommandHandler,
                            UpdateAssetCommandHandler updateAssetCommandHandler,
                            DeleteAssetCommandHandler deleteAssetCommandHandler) {
        this.listAllAssetsCommandHandler = listAllAssetsCommandHandler;
        this.getAssetCommandHandler = getAssetCommandHandler;
        this.createAssetCommandHandler = createAssetCommandHandler;
        this.updateAssetCommandHandler = updateAssetCommandHandler;
        this.deleteAssetCommandHandler = deleteAssetCommandHandler;
    }

    @Override
    public ListAllAssetResponse listAllAssets(ListAllAssetCommand command) {
        return listAllAssetsCommandHandler.handler(command);
    }

    @Override
    public AssetResponse getAsset(GetAssetCommand command) {
        return getAssetCommandHandler.handler(command);
    }

    @Override
    public AssetResponse createAsset(CreateAssetCommand command) {
        return createAssetCommandHandler.handler(command);
    }

    @Override
    public AssetResponse updateAsset(UpdateAssetCommand command) {
        return updateAssetCommandHandler.handler(command);
    }

    @Override
    public void deleteAsset(DeleteAssetCommand command) {
        deleteAssetCommandHandler.handler(command);
    }
}
