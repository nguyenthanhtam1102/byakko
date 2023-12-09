package com.byakko.service.sales.service.product.dtos.assets;

import com.byakko.service.sales.common.application.dto.ListAllResponse;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ListAllAssetResponse extends ListAllResponse<AssetResponse> {
}
