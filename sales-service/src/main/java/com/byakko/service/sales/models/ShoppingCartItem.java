package com.byakko.service.sales.models;

import com.byakko.common.DomainConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "shopping_cart_items")
public class ShoppingCartItem {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Column(name = "customer_id", nullable = false)
    private String customer;

    @Column(name = "product_id", nullable = false)
    private String product;

    @Column(name = "variant_id")
    private String variant;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "modified_date", nullable = false)
    private ZonedDateTime modifiedDate;

    public ShoppingCartItem() {
        id = UUID.randomUUID().toString().replace("-", "");
        modifiedDate = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID));
    }

}
