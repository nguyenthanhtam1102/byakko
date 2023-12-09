package com.byakko.service.sales.service.product.dtos.good_receipt;

import com.byakko.service.sales.common.application.dto.ListAllResponse;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ListAllGoodsReceiptsResponse extends ListAllResponse<GoodsReceiptMinResponse> {
}
