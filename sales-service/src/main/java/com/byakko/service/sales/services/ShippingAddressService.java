package com.byakko.service.sales.services;

import com.byakko.service.sales.dtos.shipping_address.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public interface ShippingAddressService {

    List<ShippingAddressResponse> listAllShippingAddress(@Valid ListAllShippingAddressCommand command);
    ShippingAddressResponse getShippingAddress(@Valid GetShippingAddressCommand command);
    ShippingAddressResponse createShippingAddress(@Valid CreateShippingAddressCommand command);
    ShippingAddressResponse updateShippingAddress(@Valid UpdateShippingAddressCommand command);
    void deleteShippingAddress(@Valid DeleteShippingAddressCommand command);

}
