package com.byakko.service.product.dtos.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.util.Map;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CreateProductVariantCommand {

    @NotEmpty(message = "options must be not empty")
    @JsonProperty("options")
    @EqualsAndHashCode.Include
    private Map<String, String> options;

}
