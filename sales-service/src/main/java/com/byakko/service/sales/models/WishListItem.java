package com.byakko.service.sales.models;

import com.byakko.common.DomainConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "wish_list_items")
public class WishListItem {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Column(name = "customer_id", nullable = false)
    private String customer;

    @Column(name = "product_id", nullable = false)
    private String product;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    public WishListItem() {
        id = UUID.randomUUID().toString().replace("-", "");
        createdAt = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID));
    }

}
