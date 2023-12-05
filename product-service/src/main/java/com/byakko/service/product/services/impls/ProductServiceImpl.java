package com.byakko.service.product.services.impls;

import com.byakko.common.DomainConstants;
import com.byakko.common.application.dto.ListAllResponse;
import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.common.domain.exception.ValidationException;
import com.byakko.service.product.dtos.product.*;
import com.byakko.service.product.mappers.ProductMapper;
import com.byakko.service.product.models.*;
import com.byakko.service.product.repositories.*;
import com.byakko.service.product.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final AssetRepository assetRepository;
    private final CategoryRepository categoryRepository;
    private final OptionRepository optionRepository;
    private final ProductVariantRepository productVariantRepository;
    private final ProductPriceRepository productPriceRepository;

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

        Page<Product> page = productRepository.search(
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

    @Transactional
    @Override
    public ProductResponse createProduct(CreateProductCommand command) {
        Product product = new Product();
        product.setName(command.getName());
        product.setSku(command.getSku());
        product.setBarcode(command.getBarcode());
        product.setDescription(command.getDescription());

        if(command.getAssets() != null & !command.getAssets().isEmpty()) {
            Set<Asset> assets = command.getAssets()
                    .stream()
                    .map(assetId -> assetRepository.findById(assetId)
                            .orElseThrow(() -> new NotFoundException(String.format("Asset with id %s not found", assetId))))
                    .collect(Collectors.toSet());
            product.setAssets(assets);
        }

        if(command.getCategories() != null && !command.getCategories().isEmpty()) {
            Set<Category> categories = command.getCategories()
                    .stream()
                    .map(categoryId -> categoryRepository.findById(categoryId)
                            .orElseThrow(() -> new NotFoundException(String.format("Category with id %s not found", categoryId))))
                    .collect(Collectors.toSet());
            product.setCategories(categories);
        }

        if(command.getOptions() != null && !command.getOptions().isEmpty()) {
            Set<Option> options = command.getOptions().stream().map(op -> {
                Option option = new Option();
                option.setProduct(product);
                option.setName(op.getName());

                Set<OptionValue> values = op.getValues().stream().map(valueName -> {
                    OptionValue value = new OptionValue();
                    value.setName(valueName);
                    value.setOption(option);
                    return value;
                }).collect(Collectors.toSet());

                option.setValues(values);

                return option;
            }).collect(Collectors.toSet());

            product.setOptions(options);

            if(command.getVariants() != null && !command.getVariants().isEmpty()) {
                Set<ProductVariant> variants = command.getVariants().stream().map(v -> {
                    ProductVariant productVariant = new ProductVariant();
                    productVariant.setProduct(product);

                    Set<VariantOption> variantOptions = new HashSet<>();
                    v.getOptions().forEach((optionName, valueName) -> {
                        Option option = options.stream().filter(op -> op.getName().equals(optionName)).findFirst()
                                .orElseThrow(() -> new NotFoundException(String.format("Option with name %s not found", optionName)));

                        OptionValue value = option.getValues().stream().filter(val -> val.getName().equals(valueName)).findFirst()
                                .orElseThrow(() -> new NotFoundException(String.format("Option value with name %s not found", valueName)));

                        variantOptions.add(new VariantOption(productVariant, option, value));
                    });

                    productVariant.setVariantOptions(variantOptions);

                    if(options.size() != variantOptions.size()) {
                        throw new ValidationException(Map.of("variant_options", "Số lượng variant_options không khớp với số lượng options"));
                    }

                    return productVariant;
                }).collect(Collectors.toSet());

                product.setVariants(variants);
            }
        }

        productRepository.save(product);

        return ProductMapper.toProductResponse(product);
    }

    @Transactional
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

        if(command.getAssets() != null) {
            Set<Asset> assets = command.getAssets()
                    .stream()
                    .map(assetId -> assetRepository.findById(assetId)
                            .orElseThrow(() -> new NotFoundException(String.format("Asset with id %s not found", assetId))))
                    .collect(Collectors.toSet());
            product.setAssets(assets);
        }

        if(command.getCategories() != null) {
            Set<Category> categories = command.getCategories()
                    .stream()
                    .map(categoryId -> categoryRepository.findById(categoryId)
                            .orElseThrow(() -> new NotFoundException(String.format("Category with id %s not found", categoryId))))
                    .collect(Collectors.toSet());
            product.setCategories(categories);
        }

        if(command.getOptions() != null) {
            deleteAllOptions(product.getOptions());

            Set<Option> options = command.getOptions().stream().map(op -> {
                Option option = new Option();
                option.setProduct(product);
                option.setName(op.getName());

                Set<OptionValue> values = op.getValues().stream().map(valueName -> {
                    OptionValue value = new OptionValue();
                    value.setName(valueName);
                    value.setOption(option);
                    return value;
                }).collect(Collectors.toSet());

                option.setValues(values);

                return option;
            }).collect(Collectors.toSet());

            product.setOptions(options);
        }

        if(command.getVariants() != null) {
            deleteAllProductVariants(product.getVariants());

            Set<Option> options = product.getOptions()
                    .stream()
                    .filter(op -> !op.isDeleted())
                    .collect(Collectors.toSet());

            Set<ProductVariant> variants = command.getVariants().stream().map(v -> {
                ProductVariant productVariant = new ProductVariant();
                productVariant.setProduct(product);

                Set<VariantOption> variantOptions = new HashSet<>();
                v.getOptions().forEach((optionName, valueName) -> {
                    Option option = options.stream().filter(op -> op.getName().equals(optionName)).findFirst()
                            .orElseThrow(() -> new NotFoundException(String.format("Option with name %s not found", optionName)));

                    OptionValue value = option.getValues().stream().filter(val -> val.getName().equals(valueName)).findFirst()
                            .orElseThrow(() -> new NotFoundException(String.format("Option value with name %s not found", valueName)));

                    variantOptions.add(new VariantOption(productVariant, option, value));
                });

                if(options.size() != variantOptions.size()) {
                    throw new ValidationException(Map.of("variant_options", "variant_options không khớp với options"));
                }

                productVariant.setVariantOptions(variantOptions);

                return productVariant;
            }).collect(Collectors.toSet());

            product.setVariants(variants);
        }

        product.setUpdated(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)));
        productRepository.save(product);

        return ProductMapper.toProductResponse(product);
    }

    @Transactional
    @Override
    public void deleteProduct(DeleteProductCommand command) {
        Product product = productRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Product with id %s not found", command.getId())));

        deleteAllOptions(product.getOptions());
        deleteAllProductVariants(product.getVariants());
        product.setDeleted(true);

        productRepository.save(product);
    }

    private void deleteAllOptions(Iterable<Option> options) {
        options.forEach(op -> op.setDeleted(true));
        optionRepository.saveAllAndFlush(options);
    }

    private void deleteAllProductVariants(Iterable<ProductVariant> variants) {
        variants.forEach(var -> var.setDeleted(true));
        productVariantRepository.saveAllAndFlush(variants);
    }

    private ProductPrice getCurrentPriceForProduct(Product product) {
        LocalDate currentDate = LocalDate.now();
        List<ProductPrice> currentPrices = productPriceRepository.findCurrentPriceByProduct(product, currentDate);
        
        ProductPrice currentPrice = null;
        
        if(!currentPrices.isEmpty()) {
            currentPrice = currentPrices.get(0);
        } else {
            List<ProductPrice> nearestPrices = productPriceRepository.findNearestPricesBeforeCurrentDate(product, currentDate);
            if(!nearestPrices.isEmpty()) {
                currentPrice = nearestPrices.get(0);
            }
        }
        
        return currentPrice;
    }
    
}
