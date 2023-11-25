package com.byakko.service.sales.dtos.order;

import com.byakko.common.application.dto.ListAllResponse;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ListAllOrderResponse extends ListAllResponse<OrderResponse> {
}
