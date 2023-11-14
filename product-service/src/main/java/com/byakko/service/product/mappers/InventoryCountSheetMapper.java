package com.byakko.service.product.mappers;

import com.byakko.service.product.dtos.inventory_count_sheet.InventoryCountSheetDetailResponse;
import com.byakko.service.product.dtos.inventory_count_sheet.InventoryCountSheetMinResponse;
import com.byakko.service.product.dtos.inventory_count_sheet.InventoryCountSheetResponse;
import com.byakko.service.product.models.InventoryCountSheet;
import com.byakko.service.product.models.InventoryCountSheetDetail;
import com.byakko.service.product.models.Product;
import com.byakko.service.product.models.ProductVariant;

import java.util.HashMap;
import java.util.Map;

public class InventoryCountSheetMapper {

    public static InventoryCountSheetResponse toInventoryCountSheetResponse(InventoryCountSheet inventoryCountSheet) {
        return InventoryCountSheetResponse.builder()
                .id(inventoryCountSheet.getId())
                .creator(inventoryCountSheet.getCreator().getId())
                .implementer(inventoryCountSheet.getImplementer().getId())
                .asset(inventoryCountSheet.getAsset() != null ? inventoryCountSheet.getAsset().stream().map(AssetMapper::toAssetMinResponse).toList() : null)
                .note(inventoryCountSheet.getNote())
                .inventoryCountSheetDetails(inventoryCountSheet.getInventoryCountSheetDetails() != null
                        ? inventoryCountSheet.getInventoryCountSheetDetails()
                                .stream()
                                .map(InventoryCountSheetMapper::inventoryCountSheetDetailResponse)
                                .toList()
                        : null)
                .createdAt(inventoryCountSheet.getCreatedAt().toEpochSecond())
                .updatedAt(inventoryCountSheet.getUpdatedAt().toEpochSecond())
                .implementTime(inventoryCountSheet.getImplementTime() != null ? inventoryCountSheet.getImplementTime().toEpochSecond() : null)
                .build();
    }

    public static InventoryCountSheetMinResponse toInventoryCountSheetMinResponse(InventoryCountSheet inventoryCountSheet) {
        return InventoryCountSheetMinResponse.builder()
                .id(inventoryCountSheet.getId())
                .creator(inventoryCountSheet.getCreator().getId())
                .implementer(inventoryCountSheet.getImplementer().getId())
                .asset(inventoryCountSheet.getAsset() != null ? inventoryCountSheet.getAsset().stream().map(AssetMapper::toAssetMinResponse).toList() : null)
                .note(inventoryCountSheet.getNote())
                .createdAt(inventoryCountSheet.getCreatedAt().toEpochSecond())
                .updatedAt(inventoryCountSheet.getUpdatedAt().toEpochSecond())
                .implementTime(inventoryCountSheet.getImplementTime() != null ? inventoryCountSheet.getImplementTime().toEpochSecond() : null)
                .build();
    }

    public static InventoryCountSheetDetailResponse inventoryCountSheetDetailResponse(InventoryCountSheetDetail inventoryCountSheetDetail) {
        return InventoryCountSheetDetailResponse.builder()
                .id(inventoryCountSheetDetail.getId())
                .product(toInventoryCountSheetDetailResponseProduct(inventoryCountSheetDetail.getProduct(), inventoryCountSheetDetail.getProductVariant()))
                .bookInventory(inventoryCountSheetDetail.getBookInventory())
                .physicalInventory(inventoryCountSheetDetail.getPhysicalInventory())
                .note(inventoryCountSheetDetail.getNote())
                .createdAt(inventoryCountSheetDetail.getCreatedAt().toEpochSecond())
                .updatedAt(inventoryCountSheetDetail.getUpdatedAt().toEpochSecond())
                .build();
    }

    public static InventoryCountSheetDetailResponse.Product toInventoryCountSheetDetailResponseProduct(Product product, ProductVariant productVariant) {
        Map<String, String> variantOptions;
        if(productVariant != null) {
            variantOptions = new HashMap<>();
            productVariant.getVariantOptions().forEach(option -> {
                variantOptions.put(option.getOption().getName(), option.getValue().getName());
            });
        } else {
            variantOptions = null;
        }

        return InventoryCountSheetDetailResponse.Product.builder()
                .id(product.getId())
                .name(product.getName())
                .variant(productVariant != null
                        ? InventoryCountSheetDetailResponse.ProductVariant.builder()
                        .id(productVariant.getId())
                        .options(variantOptions)
                        .build()
                        : null)
                .build();
    }

}
