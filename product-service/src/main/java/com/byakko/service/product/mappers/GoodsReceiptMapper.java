package com.byakko.service.product.mappers;

import com.byakko.service.product.dtos.good_receipt.GoodsReceiptDetailResponse;
import com.byakko.service.product.dtos.good_receipt.GoodsReceiptMinResponse;
import com.byakko.service.product.dtos.good_receipt.GoodsReceiptResponse;
import com.byakko.service.product.models.GoodReceiptDetail;
import com.byakko.service.product.models.GoodsReceipt;
import com.byakko.service.product.models.Product;
import com.byakko.service.product.models.ProductVariant;

import java.util.HashMap;
import java.util.Map;

public class GoodsReceiptMapper {

    public static GoodsReceiptResponse toGoodReceiptResponse(GoodsReceipt goodsReceipt) {
        return GoodsReceiptResponse.builder()
                .id(goodsReceipt.getId())
                .purchaseOrder(goodsReceipt.getPurchaseOrder().getId())
                .employee(goodsReceipt.getEmployee().getId())
                .asset(goodsReceipt.getAsset() != null ? goodsReceipt.getAsset().stream().map(AssetMapper::toAssetMinResponse).toList() : null)
                .goodsReceiptDetails(goodsReceipt.getGoodReceiptDetails() != null
                        ? goodsReceipt.getGoodReceiptDetails()
                                .stream()
                                .map(GoodsReceiptMapper::toGoodsReceiptDetailResponse)
                                .toList()
                        : null)
                .note(goodsReceipt.getNote())
                .createdAt(goodsReceipt.getCreatedAt().toEpochSecond())
                .updatedAt(goodsReceipt.getUpdatedAt().toEpochSecond())
                .build();
    }

    public static GoodsReceiptMinResponse toGoodsReceiptMinResponse(GoodsReceipt goodsReceipt) {
        return GoodsReceiptMinResponse.builder()
                .id(goodsReceipt.getId())
                .purchaseOrder(goodsReceipt.getPurchaseOrder().getId())
                .employee(goodsReceipt.getEmployee().getId())
                .asset(goodsReceipt.getAsset() != null ? goodsReceipt.getAsset().stream().map(AssetMapper::toAssetMinResponse).toList() : null)
                .createdAt(goodsReceipt.getCreatedAt().toEpochSecond())
                .updatedAt(goodsReceipt.getUpdatedAt().toEpochSecond())
                .build();
    }

    public static GoodsReceiptDetailResponse toGoodsReceiptDetailResponse(GoodReceiptDetail goodReceiptDetail) {
        return GoodsReceiptDetailResponse.builder()
                .id(goodReceiptDetail.getId())
                .product(toGoodsReceiptDetailResponseProduct(goodReceiptDetail.getPurchaseOrderDetail().getProduct(), goodReceiptDetail.getPurchaseOrderDetail().getProductVariant()))
                .orderQuantity(goodReceiptDetail.getOrderQuantity())
                .receivedQuantity(goodReceiptDetail.getReceivedQuantity())
                .rejectedQuantity(goodReceiptDetail.getRejectedQuantity())
                .note(goodReceiptDetail.getNote())
                .createdAt(goodReceiptDetail.getCreatedAt().toEpochSecond())
                .updatedAt(goodReceiptDetail.getUpdatedAt().toEpochSecond())
                .build();
    }

    public static GoodsReceiptDetailResponse.Product toGoodsReceiptDetailResponseProduct(Product product, ProductVariant productVariant) {
        Map<String, String> variantOptions;
        if(productVariant != null) {
            variantOptions = new HashMap<>();
            productVariant.getVariantOptions().forEach(option -> {
                variantOptions.put(option.getOption().getName(), option.getValue().getName());
            });
        } else {
            variantOptions = null;
        }

        return GoodsReceiptDetailResponse.Product.builder()
                .id(product.getId())
                .name(product.getName())
                .variant(productVariant != null
                        ? GoodsReceiptDetailResponse.ProductVariant.builder()
                        .id(productVariant.getId())
                        .options(variantOptions)
                        .build()
                        : null)
                .build();
    }

}
