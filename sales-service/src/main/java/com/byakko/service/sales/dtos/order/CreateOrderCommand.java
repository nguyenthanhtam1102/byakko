package com.byakko.service.sales.dtos.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateOrderCommand {

    @NotBlank(message = "customer_id must be not blank")
    @JsonProperty("customer_id")
    private String customerId;

    @NotBlank(message = "shipping_address must be not bank")
    @JsonProperty("shipping_address")
    private String shippingAddress;

    @JsonProperty("delivery_charge")
    private BigDecimal deliveryCharge;

    private String note;

    @NotBlank(message = "phone must be not bank")
    @JsonProperty("phone")
    private String phone;

    @NotBlank(message = "payment_method must be not blank")
    @JsonProperty("payment_method")
    private String paymentMethod;

    @NotNull(message = "order_details must be not null")
    @NotEmpty(message = "order_details must be not empty")
    @JsonProperty("order_details")
    private List<CreateOrderDetailCommand> orderDetails;

}
