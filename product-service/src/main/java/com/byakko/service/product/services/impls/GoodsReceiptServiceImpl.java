package com.byakko.service.product.services.impls;

import com.byakko.common.application.dto.ListAllResponse;
import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.service.product.dtos.good_receipt.*;
import com.byakko.service.product.mappers.GoodsReceiptMapper;
import com.byakko.service.product.models.*;
import com.byakko.service.product.repositories.AssetRepository;
import com.byakko.service.product.repositories.EmployeeRepository;
import com.byakko.service.product.repositories.GoodsReceiptRepository;
import com.byakko.service.product.repositories.PurchaseOrderRepository;
import com.byakko.service.product.services.GoodsReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GoodsReceiptServiceImpl implements GoodsReceiptService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final GoodsReceiptRepository goodsReceiptRepository;
    private final EmployeeRepository employeeRepository;
    private final AssetRepository assetRepository;

    @Override
    public ListAllGoodsReceiptsResponse listAllGoodsReceipts(ListAllGoodsReceiptCommand command) {
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
            sort = Sort.by(direction, "createdAt");
        } else {
            throw new RuntimeException("Sort by not correct");
        }

        Pageable pageable = PageRequest.of(command.getPage(), command.getLimit(), sort);

        Page<GoodsReceipt> page = goodsReceiptRepository.findAll(pageable);

        return ListAllGoodsReceiptsResponse.builder()
                .data(page.stream().map(GoodsReceiptMapper::toGoodsReceiptMinResponse).toList())
                .pagination(ListAllResponse.Pagination.toPagination(page))
                .build();
    }

    @Override
    public GoodsReceiptResponse getGoodsReceipt(GetGoodsReceiptCommand command) {
        GoodsReceipt goodsReceipt = goodsReceiptRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Goods receipt with id %s not found", command.getId())));
        return GoodsReceiptMapper.toGoodReceiptResponse(goodsReceipt);
    }

    @Transactional
    @Override
    public GoodsReceiptResponse createGoodsReceipt(CreateGoodReceiptCommand command) {
        GoodsReceipt goodsReceipt = new GoodsReceipt();
        goodsReceipt.setNote(command.getNote());

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(command.getPurchaseOrder())
                .orElseThrow(() -> new NotFoundException(String.format("Purchase order with id %s not found", command.getPurchaseOrder())));
        goodsReceipt.setPurchaseOrder(purchaseOrder);

        Employee employee = employeeRepository.findById(command.getEmployee())
                .orElseThrow(() -> new NotFoundException(String.format("Employee with id %s not found", command.getEmployee())));
        goodsReceipt.setEmployee(employee);

        if(command.getAsset() != null && !command.getAsset().isEmpty()) {
            Set<Asset> assets = command.getAsset()
                    .stream()
                    .map(assetId -> assetRepository.findById(assetId)
                            .orElseThrow(() -> new NotFoundException(String.format("Asset with id %s not found", assetId))))
                    .collect(Collectors.toSet());
            goodsReceipt.setAsset(assets);
        }

        Set<GoodReceiptDetail> goodReceiptDetails = command.getGoodReceiptDetails().stream().map(grd -> {
            GoodReceiptDetail goodReceiptDetail = new GoodReceiptDetail();
            goodReceiptDetail.setGoodsReceipt(goodsReceipt);

            PurchaseOrderDetail purchaseOrderDetail = purchaseOrder.getPurchaseOrderDetails()
                    .stream()
                    .filter(pod -> pod.getId().equals(grd.getPurchaseOrderDetail()))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException(String.format("Purchase order detail with id %s not found", grd.getPurchaseOrderDetail())));

            goodReceiptDetail.setPurchaseOrderDetail(purchaseOrderDetail);
            goodReceiptDetail.setOrderQuantity(purchaseOrderDetail.getOrderQuantity());
            goodReceiptDetail.setReceivedQuantity(grd.getReceivedQuantity());
            goodReceiptDetail.setRejectedQuantity(grd.getRejectedQuantity());

            return goodReceiptDetail;
        }).collect(Collectors.toSet());

        goodsReceipt.setGoodReceiptDetails(goodReceiptDetails);

        goodsReceiptRepository.save(goodsReceipt);

        return GoodsReceiptMapper.toGoodReceiptResponse(goodsReceipt);
    }

    @Transactional
    @Override
    public GoodsReceiptResponse updateGoodsReceipt(UpdateGoodsReceiptCommand command) {
        return null;
    }

    @Override
    public void deleteGoodsReceipt(DeleteGoodReceiptCommand command) {
        GoodsReceipt goodsReceipt = goodsReceiptRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Goods receipt with id %s not found", command.getId())));
        goodsReceipt.setDeleted(true);
        goodsReceiptRepository.save(goodsReceipt);
    }

}
