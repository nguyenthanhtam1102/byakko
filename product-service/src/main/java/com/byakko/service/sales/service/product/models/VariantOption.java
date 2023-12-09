package com.byakko.service.sales.service.product.models;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "variant_options")
@IdClass(VariantOptionId.class)
public class VariantOption {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id")
    @EqualsAndHashCode.Include
    private ProductVariant variant;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    @EqualsAndHashCode.Include
    private Option option;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "value_id")
    @EqualsAndHashCode.Include
    private OptionValue value;

}
