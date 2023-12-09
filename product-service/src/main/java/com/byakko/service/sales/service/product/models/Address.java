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
@Table(name = "address")
public class Address {

    @Id
    @EqualsAndHashCode.Include
    private String id;

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

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;

    public Address() {
        id = UUID.randomUUID().toString().replace("-", "");
        createdAt = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID));
        updatedAt = createdAt;
    }

}
