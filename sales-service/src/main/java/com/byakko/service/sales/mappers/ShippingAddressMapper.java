package com.byakko.service.sales.mappers;

import com.byakko.service.sales.dtos.shipping_address.ShippingAddressResponse;
import com.byakko.service.sales.models.ShippingAddress;

public class ShippingAddressMapper {

    public static ShippingAddressResponse toShippingAddressResponse(ShippingAddress shippingAddress) {
        return ShippingAddressResponse.builder()
                .addressId(shippingAddress.getId())
                .customerId(shippingAddress.getCustomer())
                .provinceCode(shippingAddress.getProvinceCode())
                .province(shippingAddress.getProvince())
                .districtCode(shippingAddress.getDistrictCode())
                .district(shippingAddress.getDistrict())
                .communeCode(shippingAddress.getCommuneCode())
                .commune(shippingAddress.getCommune())
                .address(shippingAddress.getAddress())
                .phone(shippingAddress.getPhone())
                .note(shippingAddress.getNote())
                .isDefault(shippingAddress.isDefault())
                .build();
    }

}
