package com.byakko.service.sales.service.product.services;

import com.byakko.service.product.dtos.assets.*;
import com.byakko.service.sales.service.product.dtos.assets.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public interface AssetService {

    ListAllAssetResponse listAllAssets(@Valid ListAllAssetCommand command);
    AssetResponse getAsset(@Valid GetAssetCommand command);
    AssetResponse createAsset(@Valid CreateAssetCommand command);
    AssetResponse updateAsset(@Valid UpdateAssetCommand command);
    void deleteAsset(@Valid DeleteAssetCommand command);

}
