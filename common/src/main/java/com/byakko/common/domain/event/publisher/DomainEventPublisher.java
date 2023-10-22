package com.byakko.common.domain.event.publisher;

import com.byakko.common.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);

}
