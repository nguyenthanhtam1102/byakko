package com.byakko.service.sales.service.product.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class VariantOptionId implements Serializable {

    @EqualsAndHashCode.Include
    private ProductVariant variant;

    @EqualsAndHashCode.Include
    private Option option;

    @EqualsAndHashCode.Include
    private OptionValue value;

}
