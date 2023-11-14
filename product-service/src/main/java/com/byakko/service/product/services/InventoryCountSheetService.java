package com.byakko.service.product.services;

import com.byakko.service.product.dtos.inventory_count_sheet.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public interface InventoryCountSheetService {

    ListAllInventoryCountSheetResponse listAllInventoryCountSheet(@Valid ListAllInventoryCountSheetCommand command);
    InventoryCountSheetResponse getInventoryCountSheet(@Valid GetInventoryCountSheetCommand command);
    InventoryCountSheetResponse createInventoryCountSheet(@Valid CreateInventoryCountSheetCommand command);
    InventoryCountSheetResponse inventoryCount(@Valid InventoryCountCommand command);
    InventoryCountSheetResponse updateInventoryCountSheet(@Valid UpdateInventoryCountSheetCommand command);
    void deleteInventoryCountSheet(@Valid DeleteInventoryCountSheetCommand command);

}
