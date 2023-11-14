package com.byakko.service.product.models;

public enum PurchaseOrderStatus {

    PENDING_APPROVAL,
    APPROVED,
    ORDERED,
    RECEIVED,
    INSPECTED,
    REJECTED,
    STORED;

    @Override
    public String toString() {
        return switch (this) {
            case PENDING_APPROVAL -> "Chờ duyệt";
            case APPROVED -> "Đã duyệt";
            case ORDERED -> "Đã đặt hàng";
            case RECEIVED -> "Đã nhận hàng";
            case INSPECTED -> "Đã kiểm tra";
            case REJECTED -> "Hoàn trả";
            case STORED -> "Đã nhập kho";
        };
    }
}
