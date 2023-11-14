package com.byakko.service.product.services.impls;

import com.byakko.common.DomainConstants;
import com.byakko.common.application.dto.ListAllResponse;
import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.common.domain.exception.ValidationException;
import com.byakko.service.product.dtos.AddressDTO;
import com.byakko.service.product.dtos.product.ListAllProductsResponse;
import com.byakko.service.product.dtos.supplier.*;
import com.byakko.service.product.mappers.ProductMapper;
import com.byakko.service.product.mappers.SupplierMapper;
import com.byakko.service.product.models.Address;
import com.byakko.service.product.models.AdministrativeDivision;
import com.byakko.service.product.models.Product;
import com.byakko.service.product.models.Supplier;
import com.byakko.service.product.repositories.AddressRepository;
import com.byakko.service.product.repositories.AdministrativeDivisionRepository;
import com.byakko.service.product.repositories.SupplierRepository;
import com.byakko.service.product.services.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final AdministrativeDivisionRepository administrativeDivisionRepository;
    private final AddressRepository addressRepository;

    @Override
    public ListAllSuppliersResponse listAllSuppliers(ListAllSuppliersCommand command) {
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
        } else if(command.getSortBy().equalsIgnoreCase("NAME")) {
            sort = Sort.by(direction, "name");
        } else {
            throw new RuntimeException("Sort by not correct");
        }

        Pageable pageable = PageRequest.of(command.getPage(), command.getLimit(), sort);

        Page<Supplier> page = supplierRepository.search(
                "%" + (command.getQuery() != null ? command.getQuery().toLowerCase() : "") + "%",
                pageable
        );

        return ListAllSuppliersResponse.builder()
                .data(page.stream().map(SupplierMapper::toSupplierResponse).toList())
                .pagination(ListAllResponse.Pagination.toPagination(page))
                .build();
    }

    @Override
    public SupplierResponse getSupplier(GetSupplierCommand command) {
        Supplier supplier = supplierRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Supplier with id %s not found", command.getId())));
        return SupplierMapper.toSupplierResponse(supplier);
    }

    @Override
    public SupplierResponse createSupplier(CreateSupplierCommand command) {
        Supplier supplier = new Supplier();
        supplier.setName(command.getName());
        supplier.setPhone(command.getPhone());
        supplier.setEmail(command.getEmail());
        supplier.setTaxCode(command.getTaxCode());
        supplier.setNote(command.getNote());

        AddressDTO addressDTO = command.getAddress();
        if(addressDTO != null) {
            AdministrativeDivision province = administrativeDivisionRepository.findById(addressDTO.getProvinceCode())
                    .orElseThrow(() -> new NotFoundException(String.format("Province with code %s not found", addressDTO.getProvinceCode())));
            AdministrativeDivision district = administrativeDivisionRepository.findById(addressDTO.getDistrictCode())
                    .orElseThrow(() -> new NotFoundException(String.format("District with code %s not found", addressDTO.getDistrictCode())));
            AdministrativeDivision commune = administrativeDivisionRepository.findById(addressDTO.getCommuneCode())
                    .orElseThrow(() -> new NotFoundException(String.format("Commune with code %s not found", addressDTO.getCommuneCode())));

            Address addr = new Address();
            addr.setProvince(province);
            addr.setDistrict(district);
            addr.setCommune(commune);
            addr.setAddress(addressDTO.getAddress());

            supplier.setAddress(addr);
        }

        supplierRepository.save(supplier);

        return SupplierMapper.toSupplierResponse(supplier);
    }

    @Override
    public SupplierResponse updateSupplier(UpdateSupplierCommand command) {
        Supplier supplier = supplierRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Supplier with id %s not found", command.getId())));

        if(command.getName() != null) {
            if(command.getName().isBlank())
                throw new ValidationException(Map.of("name", "name must be not blank"));
            supplier.setName(command.getName());
        }

        if(command.getPhone() != null) {
            if(command.getPhone().isBlank())
                throw new ValidationException(Map.of("phone", "phone must be not blank"));
            supplier.setPhone(command.getPhone());
        }

        if(command.getEmail() != null) {
            if(command.getEmail().isBlank())
                throw new ValidationException(Map.of("email", "email must be not blank"));
            supplier.setEmail(command.getEmail());
        }

        if(command.getTaxCode() != null) {
            if(command.getTaxCode().isBlank())
                throw new ValidationException(Map.of("tax_code", "tax_code must be not blank"));
            supplier.setTaxCode(command.getTaxCode());
        }

        AddressDTO addressDTO = command.getAddress();
        if(addressDTO != null) {
            AdministrativeDivision province = administrativeDivisionRepository.findById(addressDTO.getProvinceCode())
                    .orElseThrow(() -> new NotFoundException(String.format("Province with code %s not found", addressDTO.getProvinceCode())));
            AdministrativeDivision district = administrativeDivisionRepository.findById(addressDTO.getDistrictCode())
                    .orElseThrow(() -> new NotFoundException(String.format("District with code %s not found", addressDTO.getDistrictCode())));
            AdministrativeDivision commune = administrativeDivisionRepository.findById(addressDTO.getCommuneCode())
                    .orElseThrow(() -> new NotFoundException(String.format("Commune with code %s not found", addressDTO.getCommuneCode())));

            Address address = supplier.getAddress();
            address.setProvince(province);
            address.setDistrict(district);
            address.setCommune(commune);
            address.setAddress(addressDTO.getAddress());
            address.setUpdatedAt(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)));
        }

        if(command.getNote() != null) {
            supplier.setNote(command.getNote());
        }

        supplier.setUpdatedAt(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)));
        supplierRepository.save(supplier);

        return SupplierMapper.toSupplierResponse(supplier);
    }

    @Override
    public void deleteSupplier(DeleteSupplierCommand command) {
        Supplier supplier = supplierRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Supplier with id %s not found", command.getId())));
        supplier.setDeleted(true);
        supplierRepository.save(supplier);
    }

}
