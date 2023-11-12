package com.byakko.service.product.services;

import com.byakko.service.product.dtos.supplier.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public interface SupplierService {

    ListAllSuppliersResponse listAllSuppliers(@Valid ListAllSuppliersCommand command);
    SupplierResponse getSupplier(@Valid GetSupplierCommand command);
    SupplierResponse createSupplier(@Valid CreateSupplierCommand command);
    SupplierResponse updateSupplier(@Valid UpdateSupplierCommand command);
    void deleteSupplier(@Valid DeleteSupplierCommand command);

}
