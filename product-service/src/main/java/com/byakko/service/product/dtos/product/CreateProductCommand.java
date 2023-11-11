package com.byakko.service.product.dtos.product;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class CreateProductCommand {

    @NotBlank(message = "name must be not blank")
    private String name;
    private String sku;
    private String barcode;
    private String description;
    private Set<String> categories;

}
