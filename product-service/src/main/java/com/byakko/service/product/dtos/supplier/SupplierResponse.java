package com.byakko.service.product.dtos.supplier;

import com.byakko.service.product.dtos.AddressDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SupplierResponse {

    private String id;
    private String name;
    private String phone;
    private String email;

    @JsonProperty("tax_code")
    private String taxCode;

    private AddressDTO address;
    private String note;

}
