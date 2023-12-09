package com.byakko.service.sales.service.production.dataaccess.entity;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "categories")
public class ProductCategoryEntity {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Column(length = 60, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ProductCategoryEntity parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<ProductCategoryEntity> children;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    public ProductCategoryEntity(String id) {
        this.id = id;
    }
}
