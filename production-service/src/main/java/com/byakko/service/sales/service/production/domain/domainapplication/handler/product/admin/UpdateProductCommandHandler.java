package com.byakko.service.sales.service.production.domain.domainapplication.handler.product.admin;

import com.byakko.service.sales.common.DomainConstants;
import com.byakko.service.sales.common.domain.exception.NotFoundException;
import com.byakko.service.sales.common.domain.exception.ValidationException;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.admin.ProductResponse;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.admin.UpdateProductCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.handler.product.ProductCommandHandlerHelper;
import com.byakko.service.sales.service.production.domain.domainapplication.mapper.ProductMapper;
import com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository.ProductRepository;
import com.byakko.service.sales.service.production.domain.domaincore.entity.Product;
import com.byakko.service.sales.service.production.domain.domaincore.valueobject.AssetId;
import com.byakko.service.sales.service.production.domain.domaincore.valueobject.ProductId;
import com.byakko.service.sales.service.production.domain.domaincore.valueobject.ProductStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UpdateProductCommandHandler {

    private final ProductRepository productRepository;
    private final ProductCommandHandlerHelper productCommandHandlerHelper;

    public UpdateProductCommandHandler(ProductRepository productRepository,
                                       ProductCommandHandlerHelper productCommandHandlerHelper) {
        this.productRepository = productRepository;
        this.productCommandHandlerHelper = productCommandHandlerHelper;
    }

    @Transactional
    public ProductResponse handler(UpdateProductCommand command) {
        Product product = productCommandHandlerHelper.findProductById(new ProductId(command.getId()));

        if(command.getBarcode() != null) {
            product.setBarcode(command.getBarcode());
        }

        if(command.getSku() != null) {
            product.setSku(command.getSku());
        }

        if(command.getSlug() != null) {
            product.setSlug(command.getSlug());
        }

        if(command.getName() != null) {
            product.setName(command.getName());
        }

        if(command.getDescription() != null) {
            product.setDescription(command.getDescription());
        }

        if(command.getStatus() != null) {
            try {
                product.setStatus(ProductStatus.valueOf(command.getStatus()));
            } catch (Exception ex) {
                throw new ValidationException(Map.of("status", "status is incorrect or does not exist"));
            }
        }

        if(command.getAssets() != null) {
            if(!product.getAssets().isEmpty()) {
                product.setImage(productCommandHandlerHelper.findAssetById(new AssetId(command.getAssets().stream().findFirst().get())));
            }

            product.setAssets(
                    command.getAssets()
                            .stream()
                            .map(assetId -> productCommandHandlerHelper.findAssetById(new AssetId(assetId)))
                            .collect(Collectors.toSet())
            );
        }

        if(command.getRelatedProducts() != null) {
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

        product.validate();
        product.setUpdatedAt(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)));

        productRepository.save(product);

        return ProductMapper.toProductResponse(product);
    }

}
