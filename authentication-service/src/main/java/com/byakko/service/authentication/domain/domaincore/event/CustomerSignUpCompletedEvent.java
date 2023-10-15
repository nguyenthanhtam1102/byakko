package com.byakko.service.authentication.domain.domaincore.event;

import com.byakko.common.domain.event.DomainEvent;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;

import java.time.ZonedDateTime;

public class CustomerSignUpCompletedEvent implements DomainEvent<Customer> {

    private final Customer customer;
    private final ZonedDateTime createdAt;

    public CustomerSignUpCompletedEvent(Customer customer, ZonedDateTime createdAt) {
        this.customer = customer;
        this.createdAt = createdAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

}
