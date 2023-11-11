package com.byakko.service.product.dtos.product;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class UpdateProductCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

    private String name;
    private String barcode;
    private String sku;
    private String description;
    private Set<String> categories;

    public UpdateProductCommand(String id) {
        this.id = id;
    }
}
