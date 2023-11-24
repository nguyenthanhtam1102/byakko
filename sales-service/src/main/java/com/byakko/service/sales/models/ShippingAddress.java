package com.byakko.service.sales.models;

import com.byakko.common.DomainConstants;
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
@Table(name = "shipping_addresses")
public class ShippingAddress {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Column(name = "customer_id", nullable = false)
    private String customer;

    @Column(name = "province_code", length = 10, nullable = false)
    private String provinceCode;

    @Column(length = 60, nullable = false)
    private String province;

    @Column(name = "district_code", length = 10, nullable = false)
    private String districtCode;

    @Column(length = 60, nullable = false)
    private String district;

    @Column(name = "commune_code", length = 10, nullable = false)
    private String communeCode;

    @Column(length = 60, nullable = false)
    private String commune;

    @Column(length = 255)
    private String address;

    @Column(length = 15, nullable = false)
    private String phone;

    @Column(length = 255)
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
