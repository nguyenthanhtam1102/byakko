package com.byakko.service.sales.service.production.domain.domainapplication.port.input;

import com.byakko.service.production.domain.domainapplication.dto.asset.*;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.asset.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Component
@Validated
public interface AssetService {

    ListAllAssetResponse listAllAssets(@Valid ListAllAssetCommand command);
    AssetResponse getAsset(@Valid GetAssetCommand command);
    AssetResponse createAsset(@Valid CreateAssetCommand command);
    AssetResponse updateAsset(@Valid UpdateAssetCommand command);
    void deleteAsset(@Valid DeleteAssetCommand command);

}
