package com.byakko.service.sales.service.production.domain.domainapplication.dto.product.admin;

import com.byakko.service.sales.common.application.dto.ListAllResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class ProductPriceHistoriesResponse extends ListAllResponse<ProductPriceHistoryResponse> {

}
