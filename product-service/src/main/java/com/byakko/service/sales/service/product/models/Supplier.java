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
@Table(name = "suppliers")
public class Supplier {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String name;
    private String phone;
    private String email;
    private String taxCode;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    private String note;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;

    private boolean deleted;

    public Supplier() {
        id = UUID.randomUUID().toString().replace("-", "");
        createdAt = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID));
        updatedAt = createdAt;
    }
}
