package com.byakko.service.sales.services.impls;

import com.byakko.common.DomainConstants;
import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.service.sales.dtos.shipping_address.*;
import com.byakko.service.sales.mappers.ShippingAddressMapper;
import com.byakko.service.sales.models.ShippingAddress;
import com.byakko.service.sales.repositories.ShippingAddressRepository;
import com.byakko.service.sales.services.ShippingAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ShippingAddressServiceImpl implements ShippingAddressService {

    private final ShippingAddressRepository shippingAddressRepository;

    @Override
    public List<ShippingAddressResponse> listAllShippingAddress(ListAllShippingAddressCommand command) {
        List<ShippingAddress> shippingAddresses = shippingAddressRepository.findAllByCustomerOrderByCreatedAtDesc(command.getCustomerId());
        return shippingAddresses.stream().map(ShippingAddressMapper::toShippingAddressResponse).toList();
    }

    @Override
    public ShippingAddressResponse getShippingAddress(GetShippingAddressCommand command) {
        return shippingAddressRepository.findById(command.getAddressId())
                .map(ShippingAddressMapper::toShippingAddressResponse)
                .orElseThrow(() -> new NotFoundException(String.format("Shipping address with id %s not found", command.getAddressId())));
    }

    @Override
    public ShippingAddressResponse createShippingAddress(CreateShippingAddressCommand command) {
        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setCustomer(command.getCustomerId());
        shippingAddress.setProvinceCode(command.getProvinceCode());
        shippingAddress.setProvince(command.getProvince());
        shippingAddress.setDistrictCode(command.getDistrictCode());
        shippingAddress.setDistrict(command.getDistrict());
        shippingAddress.setCommuneCode(command.getCommuneCode());
        shippingAddress.setCommune(command.getCommune());
        shippingAddress.setAddress(command.getAddress());
        shippingAddress.setPhone(command.getPhone());
        shippingAddress.setNote(command.getNote());
        shippingAddress.setName((command.getName()));
        shippingAddress.setDefault(command.isDefault());

        if(command.isDefault()) {
            List<ShippingAddress> shippingAddresses = shippingAddressRepository.findAllByCustomerAndDefaultIsTrue(command.getCustomerId());
            if(!shippingAddresses.isEmpty()) {
                shippingAddresses.forEach(sa -> sa.setDefault(false));
                shippingAddressRepository.saveAll(shippingAddresses);
            }
        }

        shippingAddressRepository.save(shippingAddress);

        return ShippingAddressMapper.toShippingAddressResponse(shippingAddress);
    }

    @Override
    public ShippingAddressResponse updateShippingAddress(UpdateShippingAddressCommand command) {
        ShippingAddress shippingAddress = shippingAddressRepository.findById(command.getAddressId())
                .orElseThrow(() -> new NotFoundException(String.format("Shipping address with id %s not found", command.getAddressId())));

        if(command.getProvinceCode() != null && command.getProvince() != null) {
            shippingAddress.setProvinceCode(command.getProvinceCode());
            shippingAddress.setProvince(command.getProvince());
        }

        if(command.getDistrictCode() != null && command.getDistrict() != null) {
            shippingAddress.setDistrictCode(command.getDistrictCode());
            shippingAddress.setDistrict(command.getDistrict());
        }

        if(command.getCommuneCode() != null && command.getCommune() != null) {
            shippingAddress.setCommuneCode(command.getCommuneCode());
            shippingAddress.setCommune(command.getCommune());
        }

        if(command.getAddress() != null) {
            shippingAddress.setAddress(command.getAddress());
        }

        if(command.getPhone() != null) {
            shippingAddress.setPhone(command.getPhone());
        }
        if(command.getName() != null){
            shippingAddress.setName(command.getName());
        }
        if(command.getNote() != null) {
            shippingAddress.setNote(command.getNote());
        }

        if(command.getIsDefault() != null) {
            shippingAddress.setDefault(command.getIsDefault());

            if(command.getIsDefault()) {
                List<ShippingAddress> shippingAddresses = shippingAddressRepository.findAllByCustomerAndDefaultIsTrue(command.getCustomerId());
                if(!shippingAddresses.isEmpty()) {
                    shippingAddresses.forEach(sa -> sa.setDefault(false));
                    shippingAddressRepository.saveAll(shippingAddresses);
                }
            }
        }

        shippingAddress.setUpdatedAt(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)));
        shippingAddressRepository.save(shippingAddress);

        return ShippingAddressMapper.toShippingAddressResponse(shippingAddress);
    }

    @Override
    public void deleteShippingAddress(DeleteShippingAddressCommand command) {
        ShippingAddress shippingAddress = shippingAddressRepository.findById(command.getAddressId())
                .orElseThrow(() -> new NotFoundException(String.format("Shipping address with id %s not found", command.getAddressId())));
        shippingAddressRepository.delete(shippingAddress);
    }
}
