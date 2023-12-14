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
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "option_values")
public class OptionValue {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Column(length = 60, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id", nullable = false)
    @JsonIgnore
    private Option option;

    @Column(name = "created_at", nullable = false)
    @JsonIgnore
    private ZonedDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @JsonIgnore
    private ZonedDateTime updatedAt;

    public OptionValue() {
        id = UUID.randomUUID().toString().replace("-", "");
        createdAt = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID));
        updatedAt = createdAt;
    }

}
