package com.byakko.service.product.dtos.supplier;

import com.byakko.service.product.dtos.AddressDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CreateSupplierCommand {

    @NotBlank(message = "name must be not blank")
    private String name;

    @NotBlank(message = "phone must be not blank")
    private String phone;

    @Email
    private String email;

    @JsonProperty("tax_code")
    private String taxCode;

    private AddressDTO address;

    private String note;

}
