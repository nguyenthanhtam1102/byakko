package com.byakko.service.sales.service.product.services;

import com.byakko.service.product.dtos.purchase_order.*;
import com.byakko.service.sales.service.product.dtos.purchase_order.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public interface PurchaseOrderService {

    ListAllPurchaseOrderResponse listAllPurchaseOrder(@Valid ListAllPurchaseOrderCommand command);
    PurchaseOrderResponse getPurchaseOrder(@Valid GetPurchaseOrderCommand command);
    PurchaseOrderResponse createPurchaseOrder(@Valid CreatePurchaseOrderCommand command);
    PurchaseOrderResponse updateOrderResponse(@Valid UpdatePurchaseOrderCommand command);
    void deletePurchaseOrder(@Valid DeletePurchaseOrderCommand command);

}
