package com.byakko.service.sales.common.domain.event.publisher;

import com.byakko.service.sales.common.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);

}
