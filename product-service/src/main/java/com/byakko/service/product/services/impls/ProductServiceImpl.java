package com.byakko.service.product.services.impls;

import com.byakko.common.DomainConstants;
import com.byakko.common.application.dto.ListAllResponse;
import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.common.domain.exception.ValidationException;
import com.byakko.service.product.dtos.product.*;
import com.byakko.service.product.mappers.ProductMapper;
import com.byakko.service.product.models.Category;
import com.byakko.service.product.models.Product;
import com.byakko.service.product.repositories.CategoryRepository;
import com.byakko.service.product.repositories.ProductRepository;
import com.byakko.service.product.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ListAllProductsResponse listAllProducts(ListAllProductsCommand command) {
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

        Page<Product> page = productRepository.findAllByIdOrName(
                "%" + (command.getQuery() != null ? command.getQuery().toLowerCase() : "") + "%",
                pageable
        );

        return ListAllProductsResponse.builder()
                .data(page.stream().map(ProductMapper::toProductMinResponse).toList())
                .pagination(ListAllResponse.Pagination.toPagination(page))
                .build();
    }

    @Override
    public ProductResponse getProduct(GetProductCommand command) {
        Product product = productRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Product with id %s not found", command.getId())));
        return ProductMapper.toProductResponse(product);
    }

    @Override
    public ProductResponse createProduct(CreateProductCommand command) {
        Product product = new Product();
        product.setName(command.getName());
        product.setSku(command.getSku());
        product.setBarcode(command.getBarcode());
        product.setDescription(command.getDescription());

        if(command.getCategories() != null && !command.getCategories().isEmpty()) {
            Set<Category> categories = command.getCategories()
                    .stream()
                    .map(categoryId -> categoryRepository.findById(categoryId)
                            .orElseThrow(() -> new NotFoundException(String.format("Category with id %s not found", categoryId))))
                    .collect(Collectors.toSet());
            product.setCategories(categories);
        }

        productRepository.save(product);

        return ProductMapper.toProductResponse(product);
    }

    @Override
    public ProductResponse updateProduct(UpdateProductCommand command) {
        Product product = productRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Product with id %s not found", command.getId())));

        if(command.getName() != null) {
            if(command.getName().isBlank())
                throw new ValidationException(Map.of("name", "name must be not blank"));
            product.setName(command.getName());
        }

        if(command.getSku() != null) {
            if(command.getSku().isBlank())
                throw new ValidationException(Map.of("sku", "sku must be not blank"));
            product.setSku(command.getSku());
        }

        if(command.getBarcode() != null) {
            if(command.getBarcode().isBlank())
                throw new ValidationException(Map.of("barcode", "barcode must be not blank"));
            product.setBarcode(command.getBarcode());
        }

        if(command.getDescription() != null) {
            product.setDescription(command.getDescription());
        }

        if(command.getCategories() != null) {
            Set<Category> categories = command.getCategories()
                    .stream()
                    .map(categoryId -> categoryRepository.findById(categoryId)
                            .orElseThrow(() -> new NotFoundException(String.format("Category with id %s not found", categoryId))))
                    .collect(Collectors.toSet());
            product.setCategories(categories);
        }

        product.setUpdated(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)));
        productRepository.save(product);

        return ProductMapper.toProductResponse(product);
    }

    @Override
    public void deleteProduct(DeleteProductCommand command) {
        Product product = productRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Product with id %s not found", command.getId())));
        product.setDeleted(true);
        productRepository.save(product);
    }


}
