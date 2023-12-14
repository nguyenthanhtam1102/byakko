package com.byakko.service.sales.events;

import com.byakko.common.domain.event.DomainEvent;
import com.byakko.service.sales.models.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreatedEvent implements DomainEvent<Order> {

    private String id;
    private Order order;
    private Long timestamp;

}
