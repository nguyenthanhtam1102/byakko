package com.byakko.service.product.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AddressDTO {

    @JsonProperty("province_code")
    private String provinceCode;

    @JsonProperty("district_code")
    private String districtCode;

    @JsonProperty("commune_code")
    private String communeCode;

    private String address;

}
