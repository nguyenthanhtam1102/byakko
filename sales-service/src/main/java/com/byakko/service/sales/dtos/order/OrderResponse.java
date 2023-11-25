package com.byakko.service.sales.dtos.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class OrderResponse {

    private String id;

    @JsonProperty("order_date")
    private Long orderDate;

    private String customer;

    @JsonProperty("shipping_address")
    private String shippingAddress;

    private String note;

    @JsonProperty("order_status")
    private String orderStatus;

    @JsonProperty("payment_method")
    private String paymentMethod;

    @JsonProperty("sub_total")
    private BigDecimal subTotal;

    @JsonProperty("delivery_charge")
    private BigDecimal deliveryCharge;

    @JsonProperty("total_due")
    private BigDecimal totalDue;

    @JsonProperty("order_details")
    private List<OrderDetailResponse> orderDetails;

    @JsonProperty("status_histories")
    private List<OrderStatusHistoryResponse> statusHistories;

}
