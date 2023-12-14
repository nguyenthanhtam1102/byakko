package com.byakko.service.product.events;

public enum OrderStatus {

    CREATED,
    PENDING_PAYMENT,
    PAYMENT_SUCCESS,
    PENDING_APPROVAL,
    APPROVAL,
    PROCESSING,
    TRANSPORTING,
    DELIVERED,
    COMPLETED,
    CANCELED,

}
