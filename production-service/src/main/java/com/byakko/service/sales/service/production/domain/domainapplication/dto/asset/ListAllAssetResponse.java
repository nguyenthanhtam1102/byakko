package com.byakko.service.sales.service.production.domain.domainapplication.dto.asset;

import com.byakko.service.sales.common.application.dto.ListAllResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class ListAllAssetResponse extends ListAllResponse<AssetResponse> {
}
