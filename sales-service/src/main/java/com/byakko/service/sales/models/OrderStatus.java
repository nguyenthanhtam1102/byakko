package com.byakko.service.sales.models;

public enum OrderStatus {

    PENDING,
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
    FAILED,

}
