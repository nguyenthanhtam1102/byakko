package com.byakko.service.production.domain.domainapplication.handler.product.admin;

import com.byakko.common.DomainConstants;
import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.common.domain.exception.ValidationException;
import com.byakko.common.valueobject.Money;
import com.byakko.service.production.domain.domainapplication.dto.product.admin.ProductResponse;
import com.byakko.service.production.domain.domainapplication.dto.product.admin.UpdateProductCommand;
import com.byakko.service.production.domain.domainapplication.handler.product.ProductCommandHandlerHelper;
import com.byakko.service.production.domain.domainapplication.mapper.ProductMapper;
import com.byakko.service.production.domain.domainapplication.port.output.repository.ProductPriceHistoryRepository;
import com.byakko.service.production.domain.domainapplication.port.output.repository.ProductRepository;
import com.byakko.service.production.domain.domaincore.entity.Product;
import com.byakko.service.production.domain.domaincore.entity.ProductPriceHistory;
import com.byakko.service.production.domain.domaincore.valueobject.AssetId;
import com.byakko.service.production.domain.domaincore.valueobject.ProductId;
import com.byakko.service.production.domain.domaincore.valueobject.ProductStatus;
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
    private final ProductPriceHistoryRepository productPriceHistoryRepository;

    public UpdateProductCommandHandler(ProductRepository productRepository,
                                       ProductCommandHandlerHelper productCommandHandlerHelper,
                                       ProductPriceHistoryRepository productPriceHistoryRepository) {
        this.productRepository = productRepository;
        this.productCommandHandlerHelper = productCommandHandlerHelper;
        this.productPriceHistoryRepository = productPriceHistoryRepository;
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

        if(command.getOriginalPrice() != null) {
            product.setOriginalPrice(new Money(command.getOriginalPrice()));
        }

        if(command.getPrice() != null) {
            product.setPrice(new Money(command.getPrice()));
        }

        if(command.getPricePerItem() != null) {
            product.setPricePerItem(new Money(command.getPricePerItem()));
        }

        if(command.getStatus() != null) {
            try {
                ProductStatus.valueOf(command.getStatus());
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

        ProductPriceHistory priceHistory = productPriceHistoryRepository.getLastedPriceForProduct(product)
                .orElseThrow(() -> new NotFoundException(String.format("lasted price for product %s not found", product.getId().getValue())));

        if(product.getOriginalPrice().compare(priceHistory.getOriginalPrice()) != 0
            || product.getPrice().compare(priceHistory.getPrice()) != 0
            || product.getPricePerItem().compare(priceHistory.getPricePerItem()) != 0) {

            // Update lại thời gian kết thúc của price history trước đó
            priceHistory.setEndDate(product.getUpdatedAt());
            productPriceHistoryRepository.save(priceHistory);

            // Tạo ra một lasted price
            ProductPriceHistory newPriceHistory = ProductPriceHistory.Builder.builder()
                    .product(product)
                    .originalPrice(product.getOriginalPrice())
                    .price(product.getPrice())
                    .pricePerItem(product.getPricePerItem())
                    .build();

            newPriceHistory.initialize();
            newPriceHistory.validate();

            productPriceHistoryRepository.save(newPriceHistory);
        }

        return ProductMapper.toProductResponse(product);
    }

}
