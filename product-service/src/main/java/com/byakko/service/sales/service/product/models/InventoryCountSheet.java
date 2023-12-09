package com.byakko.service.sales.service.product.models;

import com.byakko.service.sales.common.DomainConstants;
import lombok.*;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "inventory_count_sheets")
public class InventoryCountSheet {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", nullable = false)
    private Employee creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "implementer_id")
    private Employee implementer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "inventory_count_sheets_to_assets",
            joinColumns =@JoinColumn(name = "inventory_count_sheet_id"),
            inverseJoinColumns = @JoinColumn(name = "asset_id")
    )
    private Set<Asset> asset;

    private String note;

    @OneToMany(mappedBy = "inventoryCountSheet", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<InventoryCountSheetDetail> inventoryCountSheetDetails;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;

    @Column(name = "implement_time")
    private ZonedDateTime implementTime;

    private boolean deleted;

    public InventoryCountSheet() {
        id = UUID.randomUUID().toString().replace("-", "");
        createdAt = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID));
        updatedAt = createdAt;
    }

}
