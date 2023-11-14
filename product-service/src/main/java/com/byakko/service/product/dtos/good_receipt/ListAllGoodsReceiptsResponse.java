package com.byakko.service.product.dtos.good_receipt;

import com.byakko.common.application.dto.ListAllResponse;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ListAllGoodsReceiptsResponse extends ListAllResponse<GoodsReceiptMinResponse> {
}
