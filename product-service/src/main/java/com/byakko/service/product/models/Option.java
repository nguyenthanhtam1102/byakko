package com.byakko.service.product.models;

import com.byakko.common.DomainConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@Table(name = "options")
public class Option {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    private Product product;

    @Column(length = 60, nullable = false)
    private String name;

    @OneToMany(mappedBy = "option", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<OptionValue> values;

    @Column(name = "created_at", nullable = false)
    @JsonIgnore
    private ZonedDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @JsonIgnore
    private ZonedDateTime updatedAt;

    @JsonIgnore
    private boolean deleted;

    public Option() {
        id = UUID.randomUUID().toString().replace("-", "");
        createdAt = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID));
        updatedAt = createdAt;
    }

    @Override
    public String toString() {
        return "Option{}";
    }
}
