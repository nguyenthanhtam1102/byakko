package com.byakko.service.product.services.impls;

import com.byakko.common.application.dto.ListAllResponse;
import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.service.product.dtos.purchase_order.*;
import com.byakko.service.product.mappers.PurchaseOrderMapper;
import com.byakko.service.product.models.*;
import com.byakko.service.product.repositories.*;
import com.byakko.service.product.services.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public ListAllPurchaseOrderResponse listAllPurchaseOrder(ListAllPurchaseOrderCommand command) {
        Sort.Direction direction;
        if(command.getSortDirection() == null || command.getSortDirection().isBlank()) {
            direction = Sort.Direction.DESC;
        } else if(command.getSortDirection().equalsIgnoreCase("DESC")) {
            direction = Sort.Direction.DESC;
        } else if(command.getSortDirection().equalsIgnoreCase("ASC")) {
            direction = Sort.Direction.ASC;
        } else {
            throw new RuntimeException("Sort direction not correct");
        }


        Sort sort;
        if(command.getSortBy() == null || command.getSortBy().isBlank()) {
            sort = Sort.by(direction, "orderDate");
        } else if(command.getSortBy().equalsIgnoreCase("SHIP_DATE")) {
            sort = Sort.by(direction, "shipDate");
        } else {
            throw new RuntimeException("Sort by not correct");
        }

        Pageable pageable = PageRequest.of(command.getPage(), command.getLimit(), sort);

        Page<PurchaseOrder> page = purchaseOrderRepository.findAllByIdOrSupplierOrEmployee(
                "%" + (command.getQuery() != null ? command.getQuery().toLowerCase() : "") + "%",
                pageable
        );

        return ListAllPurchaseOrderResponse.builder()
                .data(page.stream().map(PurchaseOrderMapper::toPurchaseOrderMinResponse).toList())
                .pagination(ListAllResponse.Pagination.toPagination(page))
                .build();
    }

    @Override
    public PurchaseOrderResponse getPurchaseOrder(GetPurchaseOrderCommand command) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Purchase order with id %s not found", command.getId())));
        return PurchaseOrderMapper.toPurchaseOrderResponse(purchaseOrder);
    }

    @Transactional
    @Override
    public PurchaseOrderResponse createPurchaseOrder(CreatePurchaseOrderCommand command) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setSurcharge(command.getSurcharge());
        purchaseOrder.setTax(command.getTax());
        purchaseOrder.setDeliveryCharge(command.getDeliveryCharge());
        purchaseOrder.setNote(command.getNote());
        purchaseOrder.setStatus(PurchaseOrderStatus.PENDING_APPROVAL);

//        Employee employee = employeeRepository.findById(command.getEmployee())
//                .orElseThrow(() -> new NotFoundException(String.format("Employee with id %s not found", command.getEmployee())));
//        purchaseOrder.setEmployee(employee);

        Supplier supplier = supplierRepository.findById(command.getSupplier())
                .orElseThrow(() -> new NotFoundException(String.format("Supplier with id %s not found", command.getSupplier())));
        purchaseOrder.setSupplier(supplier);

        Set<PurchaseOrderDetail> orderDetails = command.getPurchaseOrderDetails()
                .stream()
                .map(od -> {
                    PurchaseOrderDetail orderDetail = new PurchaseOrderDetail();
                    orderDetail.setPurchaseOrder(purchaseOrder);
                    orderDetail.setOrderQuantity(od.getOrderQuantity());

                    Product product = productRepository.findById(od.getProduct())
                            .orElseThrow(() -> new NotFoundException(String.format("Product with id %s not found", od.getProduct())));
                    orderDetail.setProduct(product);

                    if(od.getProductVariant() != null) {
                        ProductVariant productVariant = product.getVariants()
                                .stream()
                                .filter(pv -> pv.getId().equals(od.getProductVariant()) && !pv.isDeleted())
                                .findFirst()
                                .orElseThrow(() -> new NotFoundException(String.format("Product variant with id %s not found", od.getProductVariant())));
                        orderDetail.setProductVariant(productVariant);
                    }

                    orderDetail.setUnitPrice(od.getUnitPrice());
                    orderDetail.setLineTotal(orderDetail.getUnitPrice().multiply(BigDecimal.valueOf(orderDetail.getOrderQuantity())));

                    return orderDetail;
                }).collect(Collectors.toSet());

        purchaseOrder.setPurchaseOrderDetails(orderDetails);

        BigDecimal purchaseOrderSubTotal = orderDetails.stream().map(PurchaseOrderDetail::getLineTotal).reduce(BigDecimal.ZERO, (subTotal, item) -> item.add(subTotal));
        purchaseOrder.setSubTotal(purchaseOrderSubTotal);

        BigDecimal purchaseOrderTotalDue = purchaseOrderSubTotal.subtract(command.getDiscount()).add(command.getSurcharge()).add(command.getTax()).add(command.getDeliveryCharge());
        purchaseOrder.setTotalDue(purchaseOrderTotalDue);

        PurchaseOrder purchaseOrder1 = purchaseOrderRepository.save(purchaseOrder);
        System.out.println(purchaseOrder1.getId());
        return PurchaseOrderMapper.toPurchaseOrderResponse(purchaseOrder);
    }

    @Transactional
    @Override
    public PurchaseOrderResponse updateOrderResponse(UpdatePurchaseOrderCommand command) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Purchase order with id %s not found", command.getId())));




        return PurchaseOrderMapper.toPurchaseOrderResponse(purchaseOrder);
    }

    @Transactional
    @Override
    public void deletePurchaseOrder(DeletePurchaseOrderCommand command) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Purchase order with id %s not found", command.getId())));
        purchaseOrder.setDeleted(true);
        purchaseOrderRepository.save(purchaseOrder);
    }

}
