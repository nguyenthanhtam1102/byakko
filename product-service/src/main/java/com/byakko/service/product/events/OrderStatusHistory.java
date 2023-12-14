package com.byakko.service.product.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderStatusHistory {

    @EqualsAndHashCode.Include
    private String id;

    private Order order;

    private OrderStatus status;

    private Long timestamp;

    private String note;

}
