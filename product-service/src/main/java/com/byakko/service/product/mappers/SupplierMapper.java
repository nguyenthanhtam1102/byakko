package com.byakko.service.product.mappers;

import com.byakko.service.product.dtos.supplier.SupplierResponse;
import com.byakko.service.product.models.Supplier;

public class SupplierMapper {

    public static SupplierResponse toSupplierResponse(Supplier supplier) {
        return SupplierResponse.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .phone(supplier.getPhone())
                .email(supplier.getEmail())
                .taxCode(supplier.getTaxCode())
                .address(supplier.getAddress() != null ? AddressMapper.toAddressDTO(supplier.getAddress()) : null)
                .note(supplier.getNote())
                .build();
    }

}
