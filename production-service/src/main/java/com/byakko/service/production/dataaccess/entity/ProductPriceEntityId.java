package com.byakko.service.production.dataaccess.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class ProductPriceEntityId implements Serializable {

    @EqualsAndHashCode.Include
    private ProductEntity product;

    @EqualsAndHashCode.Include
    private ZonedDateTime startDate;

}
