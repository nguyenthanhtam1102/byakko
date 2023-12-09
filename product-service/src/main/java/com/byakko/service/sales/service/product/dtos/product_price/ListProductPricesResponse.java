package com.byakko.service.sales.service.product.dtos.product_price;

import com.byakko.service.sales.common.application.dto.ListAllResponse;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ListProductPricesResponse extends ListAllResponse<ProductPriceResponse> {

}
