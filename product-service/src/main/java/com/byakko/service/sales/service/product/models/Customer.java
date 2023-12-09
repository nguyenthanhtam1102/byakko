package com.byakko.service.sales.service.product.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<ShippingAddress> shippingAddresses;

}
