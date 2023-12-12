package com.byakko.service.sales.dtos.shipping_address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateShippingAddressCommand {

    @NotBlank(message = "customer_id must be not blank")
    @JsonProperty("customer_id")
    private String customerId;

    @NotBlank(message = "address_id must be not blank")
    @JsonProperty("address_id")
    private String addressId;

    @JsonProperty("province_code")
    private String provinceCode;

    private String province;

    @JsonProperty("district_code")
    private String districtCode;

    private String district;

    @JsonProperty("commune_code")
    private String communeCode;

    private String commune;

    @JsonProperty("address_detail")
    private String address;

    private String phone;
    private String name;
    private String note;

    @JsonProperty("is_default")
    private Boolean isDefault;

}
