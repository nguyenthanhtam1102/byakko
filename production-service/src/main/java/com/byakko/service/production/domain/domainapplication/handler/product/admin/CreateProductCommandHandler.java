package com.byakko.service.production.domain.domainapplication.handler.product.admin;

import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.common.domain.exception.ValidationException;
import com.byakko.common.valueobject.Money;
import com.byakko.service.production.domain.domainapplication.dto.product.admin.CreateProductCommand;
import com.byakko.service.production.domain.domainapplication.dto.product.admin.ProductResponse;
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

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CreateProductCommandHandler {

    private final ProductRepository productRepository;
    private final ProductCommandHandlerHelper productCommandHandlerHelper;
    private final ProductPriceHistoryRepository productPriceHistoryRepository;

    public CreateProductCommandHandler(ProductRepository productRepository,
                                       ProductCommandHandlerHelper productCommandHandlerHelper,
                                       ProductPriceHistoryRepository productPriceHistoryRepository) {
        this.productRepository = productRepository;
        this.productCommandHandlerHelper = productCommandHandlerHelper;
        this.productPriceHistoryRepository = productPriceHistoryRepository;
    }

    @Transactional
    public ProductResponse handler(CreateProductCommand command) {
        Product product = Product.Builder.builder()
                .barcode(command.getBarcode())
                .sku(command.getSku())
                .slug(command.getSlug())
                .name(command.getName())
                .description(command.getDescription())
                .originalPrice(command.getOriginalPrice() != null ? new Money(command.getOriginalPrice()) : null)
                .price(command.getPrice() != null ? new Money(command.getPrice()) : null)
                .pricePerItem(command.getPricePerItem() != null ? new Money(command.getPricePerItem()) : null)
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

        product.initialize();
        product.validate();

        productRepository.save(product);

        // Tạo ra một price history
        ProductPriceHistory priceHistory = ProductPriceHistory.Builder.builder()
                .product(product)
                .originalPrice(product.getOriginalPrice())
                .price(product.getPrice())
                .pricePerItem(product.getPricePerItem())
                .build();

        priceHistory.initialize();
        priceHistory.validate();

        productPriceHistoryRepository.save(priceHistory);

        return ProductMapper.toProductResponse(product);
    }

}
