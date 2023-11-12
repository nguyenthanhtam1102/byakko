package com.byakko.service.product.dtos.assets;

import com.byakko.common.application.dto.ListAllResponse;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ListAllAssetResponse extends ListAllResponse<AssetResponse> {
}
