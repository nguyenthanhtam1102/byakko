package com.byakko.service.production.dataaccess.adapter;

import com.byakko.common.application.dto.ListAllResponse;
import com.byakko.service.production.dataaccess.entity.ProductEntity;
import com.byakko.service.production.dataaccess.mapper.ProductMapper;
import com.byakko.service.production.dataaccess.repository.ProductJpaRepository;
import com.byakko.service.production.domain.domainapplication.dto.product.admin.ListAllProductCommand;
import com.byakko.service.production.domain.domainapplication.dto.product.admin.ListAllProductResponse;
import com.byakko.service.production.domain.domainapplication.dto.product.customer.ProductFilterCommand;
import com.byakko.service.production.domain.domainapplication.dto.product.customer.ProductFilterResponse;
import com.byakko.service.production.domain.domainapplication.port.output.repository.ProductRepository;
import com.byakko.service.production.domain.domaincore.entity.Product;
import com.byakko.service.production.domain.domaincore.valueobject.ProductId;
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

    @Override
    public ListAllProductResponse findAll(ListAllProductCommand command) {
        Pageable pageable = PageRequest.of(
                command.getPage(),
                command.getLimit(),
                Sort.by(Sort.Direction.DESC, "createdAt")
        );
        Page<ProductEntity> page = productJpaRepository.findAll(pageable);
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
                .data(page.get().map(ProductMapper::toProductFilterItemResponse).toList())
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
