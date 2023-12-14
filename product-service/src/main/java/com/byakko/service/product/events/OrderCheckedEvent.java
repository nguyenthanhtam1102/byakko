package com.byakko.service.product.events;

import com.byakko.common.domain.event.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCheckedEvent implements DomainEvent<Order> {

    private String id;
    private Order order;
    private Long timestamp;

}
