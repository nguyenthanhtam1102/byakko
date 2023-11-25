package com.byakko.service.sales.dtos.shipping_address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ShippingAddressResponse {

    @JsonProperty("customer_id")
    private String customerId;

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

    private String note;

    @JsonProperty("is_default")
    private boolean isDefault;

}
