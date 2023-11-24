package com.byakko.service.sales.dtos.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OrderStatusHistoryResponse {

    private String status;
    private Long timestamp;
    private String note;

}
