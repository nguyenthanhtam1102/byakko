package com.byakko.service.product.mappers;

import com.byakko.service.product.dtos.purchase_order.PurchaseOrderDetailResponse;
import com.byakko.service.product.dtos.purchase_order.PurchaseOrderMinResponse;
import com.byakko.service.product.dtos.purchase_order.PurchaseOrderResponse;
import com.byakko.service.product.models.Product;
import com.byakko.service.product.models.ProductVariant;
import com.byakko.service.product.models.PurchaseOrder;
import com.byakko.service.product.models.PurchaseOrderDetail;

import java.util.HashMap;
import java.util.Map;

public class PurchaseOrderMapper {

    public static PurchaseOrderResponse toPurchaseOrderResponse(PurchaseOrder order) {
        return PurchaseOrderResponse.builder()
                .id(order.getId())
//                .employee(order.getEmployee().getId())
                .supplier(order.getSupplier().getId())
                .subTotal(order.getSubTotal())
                .surcharge(order.getSurcharge())
                .tax(order.getTax())
                .deliveryCharge(order.getDeliveryCharge())
                .totalDue(order.getTotalDue())
                .orderDate(order.getOrderDate().toEpochSecond())
                .shipDate(order.getShipDate() != null
                        ? order.getShipDate().toEpochSecond()
                        :null )
                .note(order.getNote())
                .status(order.getStatus().toString())
                .purchaseOrderDetails(order.getPurchaseOrderDetails() != null
                        ? order.getPurchaseOrderDetails()
                                .stream()
                                .map(PurchaseOrderMapper::toPurchaseOrderDetailResponse)
                                .toList()
                        : null)
                .build();
    }

    public static PurchaseOrderMinResponse toPurchaseOrderMinResponse(PurchaseOrder order) {
        return PurchaseOrderMinResponse.builder()
                .id(order.getId())
//                .employee(order.getEmployee().getId())
                .supplier(order.getSupplier().getId())
                .subTotal(order.getSubTotal())
                .surcharge(order.getSurcharge())
                .tax(order.getTax())
                .deliveryCharge(order.getDeliveryCharge())
                .totalDue(order.getTotalDue())
                .orderDate(order.getOrderDate().toEpochSecond())
                .shipDate(order.getShipDate().toEpochSecond())
                .note(order.getNote())
                .status(order.getStatus().toString())
                .build();
    }

    public static PurchaseOrderDetailResponse toPurchaseOrderDetailResponse(PurchaseOrderDetail orderDetail) {
        return PurchaseOrderDetailResponse.builder()
                .id(orderDetail.getId())
                .product(toPurchaseOrderDetailsResponseProduct(orderDetail.getProduct(), orderDetail.getProductVariant()))
                .unitPrice(orderDetail.getUnitPrice())
                .orderQuantity(orderDetail.getOrderQuantity())
                .lineTotal(orderDetail.getLineTotal())
                .build();
    }

    public static PurchaseOrderDetailResponse.Product toPurchaseOrderDetailsResponseProduct(Product product, ProductVariant productVariant) {
        Map<String, String> variantOptions;
        if(productVariant != null) {
            variantOptions = new HashMap<>();
            productVariant.getVariantOptions().forEach(option -> {
                variantOptions.put(option.getOption().getName(), option.getValue().getName());
            });
        } else {
            variantOptions = null;
        }

        return PurchaseOrderDetailResponse.Product.builder()
                .id(product.getId())
                .name(product.getName())
                .variant(productVariant != null
                        ? PurchaseOrderDetailResponse.ProductVariant.builder()
                                .id(productVariant.getId())
                                .options(variantOptions)
                                .build()
                        : null)
                .build();
    }

}
