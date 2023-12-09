package com.byakko.service.sales.service.product.models;

import com.byakko.service.sales.common.DomainConstants;
import lombok.*;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "shipping_address")
public class ShippingAddress {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_code", nullable = false)
    private AdministrativeDivision province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_code", nullable = false)
    private AdministrativeDivision district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commune_code", nullable = false)
    private AdministrativeDivision commune;

    @Column(length = 255)
    private String address;

    @Column(length = 15, nullable = false)
    private String phone;

    private String note;

    @Column(name = "is_default")
    private boolean isDefault;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;

    public ShippingAddress() {
        id = UUID.randomUUID().toString().replace("-", "");
        createdAt = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID));
        updatedAt = createdAt;
    }
}
