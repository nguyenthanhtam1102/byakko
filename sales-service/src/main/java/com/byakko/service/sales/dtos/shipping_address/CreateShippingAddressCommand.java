package com.byakko.service.sales.dtos.shipping_address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateShippingAddressCommand {

    @NotBlank(message = "customer_id must be not blank")
    @JsonProperty("customer_id")
    private String customerId;

    @NotBlank(message = "province_code must be not blank")
    @JsonProperty("province_code")
    private String provinceCode;

    @NotBlank(message = "province must be not blank")
    private String province;

    @NotBlank(message = "district_code must be not blank")
    @JsonProperty("district_code")
    private String districtCode;

    @NotBlank(message = "district must be not blank")
    private String district;

    @NotBlank(message = "commune_code must be not blank")
    @JsonProperty("commune_code")
    private String communeCode;

    @NotBlank(message = "commune must be not blank")
    private String commune;

    @NotBlank(message = "address_detail must be not blank")
    @JsonProperty("address_detail")
    private String address;

    @NotBlank(message = "phone must be not blank")
    private String phone;

    private String note;

    @JsonProperty("is_default")
    private boolean isDefault;

}
