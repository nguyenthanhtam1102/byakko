package com.byakko.service.sales.service.production.domain.domainapplication.handler.product.admin;

import com.byakko.service.sales.common.domain.exception.NotFoundException;
import com.byakko.service.sales.common.domain.exception.ValidationException;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.admin.CreateProductCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.admin.ProductResponse;
import com.byakko.service.sales.service.production.domain.domainapplication.handler.product.ProductCommandHandlerHelper;
import com.byakko.service.sales.service.production.domain.domainapplication.mapper.ProductMapper;
import com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository.OptionRepository;
import com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository.ProductRepository;
import com.byakko.service.production.domain.domaincore.entity.*;
import com.byakko.service.sales.service.production.domain.domaincore.valueobject.AssetId;
import com.byakko.service.sales.service.production.domain.domaincore.valueobject.ProductId;
import com.byakko.service.sales.service.production.domain.domaincore.valueobject.ProductStatus;
import com.byakko.service.sales.service.production.domain.domaincore.entity.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CreateProductCommandHandler {

    private final ProductRepository productRepository;
    private final ProductCommandHandlerHelper productCommandHandlerHelper;
    private final OptionRepository optionRepository;

    public CreateProductCommandHandler(ProductRepository productRepository,
                                       ProductCommandHandlerHelper productCommandHandlerHelper,
                                       OptionRepository optionRepository) {
        this.productRepository = productRepository;
        this.productCommandHandlerHelper = productCommandHandlerHelper;
        this.optionRepository = optionRepository;
    }

    @Transactional
    public ProductResponse handler(CreateProductCommand command) {
        Product product = Product.Builder.builder()
                .barcode(command.getBarcode())
                .sku(command.getSku())
                .slug(command.getSlug())
                .name(command.getName())
                .description(command.getDescription())
                .build();

        if(command.getStatus() != null) {
            try {
                product.setStatus(ProductStatus.valueOf(command.getStatus()));
            } catch (Exception ex) {
                throw new ValidationException(Map.of("status", "status is incorrect or does not exist"));
            }
        }

        if(command.getAssets() != null && !command.getAssets().isEmpty()) {
            product.setImage(productCommandHandlerHelper.findAssetById(new AssetId(command.getAssets().stream().findFirst().get())));

            product.setAssets(
                    command.getAssets()
                            .stream()
                            .map(assetId -> productCommandHandlerHelper.findAssetById(new AssetId(assetId)))
                            .collect(Collectors.toSet())
            );
        }

        if(command.getRelatedProducts() != null && !command.getRelatedProducts().isEmpty()) {
            product.setRelatedProducts(
                    command.getRelatedProducts()
                            .stream()
                            .map(productId ->
                                    productRepository.findById(
                                            new ProductId(productId))
                                            .orElseThrow(() -> new NotFoundException(String.format("Related product %s not found", productId))))
                            .collect(Collectors.toSet())
            );
        }

        if(command.getOptions() != null && !command.getOptions().isEmpty()) {
            Set<Option> options = new HashSet<>();
            command.getOptions().forEach((optionName, optionValues) -> {
                Option option = Option.Builder.builder()
                                .name(optionName)
                                .build();

                Set<OptionValue> values = optionValues.stream()
                        .map(valueName -> new OptionValue(valueName, option))
                        .collect(Collectors.toSet());

                option.setValues(values);

                option.initialize();

                options.add(option);

                optionRepository.save(option);
            });

            if(command.getVariants() != null && !command.getVariants().isEmpty()) {
                Set<ProductVariant> variants = command.getVariants().stream().map(createVariantCommand -> {
                    ProductVariant productVariant = new ProductVariant();
                    productVariant.setSku(createVariantCommand.getSku());

                    Set<ProductVariantOption> variantOptions = new HashSet<>();
                    createVariantCommand.getVariantOptions().forEach((optionName, valueName) -> {
                        ProductVariantOption productVariantOption = new ProductVariantOption();
                        Option option = options.stream().filter(op -> op.getName().equals(optionName)).findFirst().orElseThrow(() -> new NotFoundException(String.format("Option %s not found", optionName)));
                        productVariantOption.setOption(option);
                        OptionValue value = option.getValues().stream().filter(vl -> vl.getName().equals(valueName)).findFirst().orElseThrow(() -> new NotFoundException(String.format("Option value %s not found", valueName)));
                        productVariantOption.setOptionValue(value);
                        variantOptions.add(productVariantOption);
                    });
                    productVariant.setVariantOptions(variantOptions);
                    return productVariant;
                }).collect(Collectors.toSet());
                product.setVariants(variants);
            }
        }

        product.initialize();
        product.validate();

        productRepository.save(product);

        return ProductMapper.toProductResponse(product);
    }

}
