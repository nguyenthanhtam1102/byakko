package com.byakko.service.product.services.impls;

import com.byakko.common.DomainConstants;
import com.byakko.common.application.dto.ListAllResponse;
import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.common.domain.exception.ValidationException;
import com.byakko.service.product.dtos.product_price.*;
import com.byakko.service.product.mappers.ProductPriceMapper;
import com.byakko.service.product.models.Employee;
import com.byakko.service.product.models.Product;
import com.byakko.service.product.models.ProductPrice;
import com.byakko.service.product.models.ProductVariant;
import com.byakko.service.product.repositories.EmployeeRepository;
import com.byakko.service.product.repositories.ProductPriceRepository;
import com.byakko.service.product.repositories.ProductRepository;
import com.byakko.service.product.repositories.ProductVariantRepository;
import com.byakko.service.product.services.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProductPriceServiceImpl implements ProductPriceService {

    private final ProductRepository productRepository;
    private final ProductVariantRepository productVariantRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductPriceRepository productPriceRepository;

    @Override
    public ListProductPricesResponse listProductPrices(ListProductPricesCommand command) {
        Product product = productRepository.findById(command.getProductId())
                .orElseThrow(() -> new NotFoundException(String.format("product with id %s not found", command.getProductId())));

        Pageable pageable = PageRequest.of(
                command.getPage(),
                command.getLimit(),
                Sort.by(Sort.Direction.DESC, "startDate")
        );

        Page<ProductPrice> page = productPriceRepository.findAllByProduct(product.getId(), pageable);

        return ListProductPricesResponse.builder()
                .data(page.get().map(ProductPriceMapper::toProductPriceResponse).toList())
                .pagination(ListAllResponse.Pagination.toPagination(page))
                .build();
    }

    @Override
    public ProductPriceResponse createProductPrice(CreateProductPriceCommand command) {
        Product product = productRepository.findById(command.getProduct())
                .orElseThrow(() -> new NotFoundException(String.format("product with id %s not found", command.getProduct())));

        ProductVariant variant = null;
        if(command.getVariant() != null) {
            variant = productVariantRepository.findByIdAndProductId(product.getId(), command.getVariant())
                    .orElseThrow(() -> new NotFoundException(String.format("Variant with id %s not found", command.getVariant())));
        }
        
//        Employee employee = employeeRepository.findById(command.getEmployee())
//                .orElseThrow(() -> new NotFoundException(String.format("employee with id %s not found", command.getEmployee())));

        ProductPrice productPrice = new ProductPrice();
        productPrice.setProduct(product);
        productPrice.setVariant(variant);
//        productPrice.setEmployee(employee);
        productPrice.setPrice(command.getPrice());
        productPrice.setNote(command.getNote());
        productPrice.setStartDate(Instant.ofEpochMilli(command.getStartDate())
                .atZone(ZoneId.of(DomainConstants.ZONE_ID))
                .toLocalDateTime()
                .toLocalDate());
        productPrice.setEndDate(command.getEndDate() != null ?
                Instant.ofEpochMilli(command.getEndDate())
                        .atZone(ZoneId.of(DomainConstants.ZONE_ID))
                        .toLocalDateTime()
                        .toLocalDate(): null);

        if(productPrice.getStartDate().isBefore(LocalDate.now())) {
            throw new ValidationException(Map.of("start_date", "start_date must not be in the past"));
        }

        if(productPrice.getEndDate() != null && productPrice.getStartDate().isAfter(productPrice.getEndDate())) {
            throw new ValidationException(Map.of("start_date","start_date must be before end_date"));
        }

        List<ProductPrice> overlappingPrices = productPriceRepository.findOverlappingPrices(product, productPrice.getStartDate(), productPrice.getEndDate());

        if(!overlappingPrices.isEmpty()) {
            throw new ValidationException(Map.of("start_date, end_date", "There are overlapping prices for this product"));
        }

        if(productPrice.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException(Map.of("price", "price must be greater than or equal 0"));
        }

        productPriceRepository.save(productPrice);

        return ProductPriceMapper.toProductPriceResponse(productPrice);
    }

    @Override
    public ProductPriceResponse updateProductPrice(UpdateProductPriceCommand command) {
        ProductPrice productPrice = productPriceRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Product price with id %s not found", command.getId())));

        if(productPrice.getStartDate().isBefore(LocalDate.now()))
            throw new RuntimeException("product price has already started so cannot be updated");

        Employee employee = employeeRepository.findById(command.getEmployee())
                .orElseThrow(() -> new NotFoundException(String.format("Employee with id %s not found", command.getEmployee())));

        productPrice.setEmployee(employee);

        if(command.getStartDate() != null) {
            productPrice.setStartDate(Instant.ofEpochSecond(command.getStartDate()).atZone(ZoneId.of(DomainConstants.ZONE_ID)).toLocalDate());
        }

        if(command.getEndDate() != null) {
            productPrice.setEndDate(Instant.ofEpochSecond(command.getEndDate()).atZone(ZoneId.of(DomainConstants.ZONE_ID)).toLocalDate());
        }

        if(productPrice.getStartDate().isBefore(LocalDate.now())) {
            throw new ValidationException(Map.of("start_date", "start_date must be in the past"));
        }

        if(productPrice.getStartDate().isAfter(productPrice.getEndDate())) {
            throw new ValidationException(Map.of("start_date, end_date", "start_date must be before end_date"));
        }

        List<ProductPrice> overlappingPrices = productPriceRepository.findOverlappingPrices(productPrice.getProduct(), productPrice.getStartDate(), productPrice.getEndDate());

        if(!overlappingPrices.isEmpty()) {
            throw new ValidationException(Map.of("start_date, end_date", "There are overlapping prices for this product"));
        }

        if(command.getPrice() != null) {
            if(command.getPrice().compareTo(BigDecimal.ZERO) < 0)
                throw new ValidationException(Map.of("price", "price must be greater than or equal 0"));
            productPrice.setPrice(command.getPrice());
        }

        if(command.getNote() != null) {
            productPrice.setNote(command.getNote());
        }

        if(command.getActive() != null) {
            productPrice.setActive(command.getActive());
        }

        productPrice.setModifiedDate(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)));
        productPriceRepository.save(productPrice);

        return ProductPriceMapper.toProductPriceResponse(productPrice);
    }

    @Override
    public void deleteProductPrice(DeleteProductPriceCommand command) {
        ProductPrice productPrice = productPriceRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Product price with id %s not found", command.getId())));

        if(productPrice.getStartDate().isBefore(LocalDate.now()))
            throw new RuntimeException("product price has already started so cannot be deleted");

        productPrice.setDeleted(true);
        productPriceRepository.save(productPrice);
    }
}
