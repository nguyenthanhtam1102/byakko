package com.byakko.service.product.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "products")
public class Product {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String barcode;

    private String name;

    private String sku;

    private String description;

}
