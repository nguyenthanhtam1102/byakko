package com.byakko.service.product.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderDetail {

    @EqualsAndHashCode.Include
    private String id;

    private String product;

    private String variant;

    private BigDecimal unitPrice;

    private Integer quantity;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id='" + id + '\'' +
                ", product='" + product + '\'' +
                ", variant='" + variant + '\'' +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                '}';
    }
}
