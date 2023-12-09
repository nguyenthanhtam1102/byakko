package com.byakko.service.sales.service.production.dataaccess.adapter;

import com.byakko.service.sales.common.application.dto.ListAllResponse;
import com.byakko.service.sales.common.domain.exception.NotFoundException;
import com.byakko.service.sales.service.production.dataaccess.entity.ProductEntity;
import com.byakko.service.sales.service.production.dataaccess.mapper.ProductMapper;
import com.byakko.service.sales.service.production.dataaccess.repository.ProductJpaRepository;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.admin.ListAllProductCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.admin.ListAllProductResponse;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.customer.ProductFilterCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.customer.ProductFilterResponse;
import com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository.ProductPriceRepository;
import com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository.ProductRepository;
import com.byakko.service.sales.service.production.domain.domaincore.entity.Product;
import com.byakko.service.sales.service.production.domain.domaincore.entity.ProductPrice;
import com.byakko.service.sales.service.production.domain.domaincore.valueobject.ProductId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductPriceRepository productPriceRepository;

    @Override
    public ListAllProductResponse findAll(ListAllProductCommand command) {
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

        Pageable pageable = PageRequest.of(
                command.getPage(),
                command.getLimit(),
                sort
        );

        Page<ProductEntity> page = productJpaRepository.findAllByIdOrName(
                "%" + command.getQuery() + "%",
                pageable
        );

        return ListAllProductResponse.builder()
                .data(page.get().map(ProductMapper::toProductItemResponse).toList())
                .pagination(ListAllResponse.Pagination.toPagination(page))
                .build();
    }

    @Override
    public ProductFilterResponse filter(ProductFilterCommand command) {
        Pageable pageable = PageRequest.of(
                command.getPage(),
                command.getLimit(),
                Sort.by(Sort.Direction.DESC, "createdAt")
        );
        Page<ProductEntity> page = productJpaRepository.findAll(pageable);
        return ProductFilterResponse.builder()
                .data(page.get().map(product -> {
                    ProductPrice productPrice = productPriceRepository.getLastedPriceForProduct(new Product(new ProductId(product.getId())))
                            .orElseThrow(() -> new NotFoundException(String.format("Price not found for product %s", product.getId())));
                    return ProductMapper.toProductFilterItemResponse(product, productPrice);
                }).toList())
                .pagination(ListAllResponse.Pagination.toPagination(page))
                .build();
    }

    @Override
    public Optional<Product> findById(ProductId id) {
        return productJpaRepository.findById(id.getValue()).map(productEntity -> {
            Product product = ProductMapper.toProduct(productEntity);
            product.setRelatedProducts(
                    productEntity.getRelatedProducts() != null
                            ? productEntity.getRelatedProducts()
                                    .stream()
                                    .map(ProductMapper::toProduct)
                                    .collect(Collectors.toSet())
                            : null);
            return product;
        });
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = productJpaRepository.save(ProductMapper.toProductEntity(product));
        Product result = ProductMapper.toProduct(productEntity);
        result.setRelatedProducts(
                productEntity.getRelatedProducts() != null
                        ? productEntity.getRelatedProducts()
                                .stream()
                                .map(ProductMapper::toProduct)
                                .collect(Collectors.toSet())
                        : null);
        return result;
    }

}
