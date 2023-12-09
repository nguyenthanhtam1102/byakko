package com.byakko.service.sales.service.product.dtos.product;

import com.byakko.service.sales.service.product.dtos.global_option.CreateOptionCommand;
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
    private Set<String> assets;
    private Set<String> categories;
    private Set<CreateOptionCommand> options;
    private Set<CreateProductVariantCommand> variants;

}
