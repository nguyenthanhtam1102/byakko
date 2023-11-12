package com.byakko.service.product.mappers;

import com.byakko.service.product.dtos.AddressDTO;
import com.byakko.service.product.models.Address;

public class AddressMapper {

    public static AddressDTO toAddressDTO(Address address) {
        return AddressDTO.builder()
                .provinceCode(address.getProvince().getCode())
                .districtCode(address.getDistrict().getCode())
                .communeCode(address.getCommune().getCode())
                .address(address.getAddress())
                .build();
    }

}
