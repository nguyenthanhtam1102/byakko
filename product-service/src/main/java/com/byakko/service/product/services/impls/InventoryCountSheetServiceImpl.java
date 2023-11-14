package com.byakko.service.product.services.impls;

import com.byakko.common.application.dto.ListAllResponse;
import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.service.product.dtos.inventory_count_sheet.*;
import com.byakko.service.product.mappers.InventoryCountSheetMapper;
import com.byakko.service.product.models.*;
import com.byakko.service.product.repositories.EmployeeRepository;
import com.byakko.service.product.repositories.InventoryCountSheetRepository;
import com.byakko.service.product.repositories.InventoryRepository;
import com.byakko.service.product.repositories.ProductRepository;
import com.byakko.service.product.services.InventoryCountSheetService;
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
public class InventoryCountSheetServiceImpl implements InventoryCountSheetService {

    private final InventoryCountSheetRepository inventoryCountSheetRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public ListAllInventoryCountSheetResponse listAllInventoryCountSheet(ListAllInventoryCountSheetCommand command) {
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
        } else if(command.getSortBy().equalsIgnoreCase("implement_time")) {
            sort = Sort.by(direction, "implementTime");
        } else {
            throw new RuntimeException("Sort by not correct");
        }

        Pageable pageable = PageRequest.of(command.getPage(), command.getLimit(), sort);

        Page<InventoryCountSheet> page = inventoryCountSheetRepository.search(
                "%" + (command.getQuery() != null ? command.getQuery().toLowerCase() : "") + "%",
                pageable
        );

        return ListAllInventoryCountSheetResponse.builder()
                .data(page.stream().map(InventoryCountSheetMapper::toInventoryCountSheetMinResponse).toList())
                .pagination(ListAllResponse.Pagination.toPagination(page))
                .build();
    }

    @Override
    public InventoryCountSheetResponse getInventoryCountSheet(GetInventoryCountSheetCommand command) {
        InventoryCountSheet inventoryCountSheet = inventoryCountSheetRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Inventory count sheet with id %s not found", command.getId())));
        return InventoryCountSheetMapper.toInventoryCountSheetResponse(inventoryCountSheet);
    }

    @Transactional
    @Override
    public InventoryCountSheetResponse createInventoryCountSheet(CreateInventoryCountSheetCommand command) {
        InventoryCountSheet inventoryCountSheet = new InventoryCountSheet();
        inventoryCountSheet.setNote(command.getNote());

        Employee creator = employeeRepository.findById(command.getCreator())
                .orElseThrow(() -> new NotFoundException(String.format("Employee creator with id %s not found", command.getCreator())));
        inventoryCountSheet.setCreator(creator);

        Set<InventoryCountSheetDetail> inventoryCountSheetDetails = command.getInventoryCountSheetDetails()
                .stream()
                .map(icsd -> {
                    InventoryCountSheetDetail inventoryCountSheetDetail = new InventoryCountSheetDetail();
                    inventoryCountSheetDetail.setInventoryCountSheet(inventoryCountSheet);
                    inventoryCountSheetDetail.setNote(icsd.getNote());

                    Product product = productRepository.findById(icsd.getProduct())
                            .orElseThrow(() -> new NotFoundException(String.format("Product with id %s not found", icsd.getProduct())));
                    inventoryCountSheetDetail.setProduct(product);

                    if(icsd.getProductVariant() != null) {
                        ProductVariant productVariant = product.getVariants()
                                .stream()
                                .filter(pv -> pv.getId().equals(icsd.getProductVariant()) && !pv.isDeleted())
                                .findFirst()
                                .orElseThrow(() -> new NotFoundException(String.format("Product variant with id %s not found", icsd.getProductVariant())));
                        inventoryCountSheetDetail.setProductVariant(productVariant);
                    }

                    Inventory inventory = inventoryRepository.getLatestInventory(icsd.getProduct(), icsd.getProductVariant())
                            .orElseThrow(() -> new NotFoundException(String.format("Inventory with product %s and product variant %s not found", icsd.getProduct(), icsd.getProductVariant())));
                    inventoryCountSheetDetail.setBookInventory(inventory.getQuantity());

                    return inventoryCountSheetDetail;
                }).collect(Collectors.toSet());

        inventoryCountSheet.setInventoryCountSheetDetails(inventoryCountSheetDetails);

        inventoryCountSheetRepository.save(inventoryCountSheet);

        return InventoryCountSheetMapper.toInventoryCountSheetResponse(inventoryCountSheet);
    }

    @Transactional
    @Override
    public InventoryCountSheetResponse inventoryCount(InventoryCountCommand command) {
        InventoryCountSheet inventoryCountSheet = inventoryCountSheetRepository.findById(command.getInventoryCountSheet())
                .orElseThrow(() -> new NotFoundException(String.format("Inventory count sheet with id %s not found", command.getInventoryCountSheet())));
        inventoryCountSheet.setNote(command.getNote());

        Employee implementer = employeeRepository.findById(command.getImplementer())
                .orElseThrow(() -> new NotFoundException(String.format("Employee implementer with id %s not found", command.getImplementer())));
        inventoryCountSheet.setImplementer(implementer);

        Set<InventoryCountSheetDetail> inventoryCountSheetDetails = command.getInventoryCountDetails()
                .stream()
                .map(icd -> {
                    InventoryCountSheetDetail inventoryCountSheetDetail = inventoryCountSheet.getInventoryCountSheetDetails()
                            .stream()
                            .filter(icsd -> icsd.getId().equals(icd.getInventoryCountSheetDetail()))
                            .findFirst()
                            .orElseThrow(() -> new NotFoundException(String.format("Inventory count sheet detail with id %s not found", icd.getInventoryCountSheetDetail())));

                    inventoryCountSheetDetail.setPhysicalInventory(icd.getPhysicalInventory());
                    inventoryCountSheetDetail.setNote(icd.getNote());

                    return inventoryCountSheetDetail;
                }).collect(Collectors.toSet());

        inventoryCountSheet.setInventoryCountSheetDetails(inventoryCountSheetDetails);

        inventoryCountSheetRepository.save(inventoryCountSheet);

        return InventoryCountSheetMapper.toInventoryCountSheetResponse(inventoryCountSheet);
    }

    @Override
    public InventoryCountSheetResponse updateInventoryCountSheet(UpdateInventoryCountSheetCommand command) {
        return null;
    }

    @Override
    public void deleteInventoryCountSheet(DeleteInventoryCountSheetCommand command) {
        InventoryCountSheet inventoryCountSheet = inventoryCountSheetRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Inventory count sheet with id %s not found", command.getId())));
        inventoryCountSheet.setDeleted(true);
        inventoryCountSheetRepository.save(inventoryCountSheet);
    }
}
