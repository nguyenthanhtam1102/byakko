package com.byakko.service.product.dtos.supplier;

import com.byakko.service.product.dtos.AddressDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateSupplierCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

    private String name;
    private String phone;
    private String email;

    @JsonProperty("tax_code")
    private String taxCode;

    private AddressDTO address;
    private String note;

}
