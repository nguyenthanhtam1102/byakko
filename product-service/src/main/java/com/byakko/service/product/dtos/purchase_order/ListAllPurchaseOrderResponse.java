package com.byakko.service.product.dtos.purchase_order;

import com.byakko.common.application.dto.ListAllResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@SuperBuilder
public class ListAllPurchaseOrderResponse extends ListAllResponse<PurchaseOrderMinResponse> {
}
