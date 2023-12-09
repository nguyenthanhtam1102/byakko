package com.byakko.service.sales.service.production.domain.domainapplication.handler.product;

import com.byakko.service.sales.common.domain.exception.NotFoundException;
import com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository.AssetRepository;
import com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository.ProductRepository;
import com.byakko.service.sales.service.production.domain.domaincore.entity.Asset;
import com.byakko.service.sales.service.production.domain.domaincore.entity.Product;
import com.byakko.service.sales.service.production.domain.domaincore.valueobject.AssetId;
import com.byakko.service.sales.service.production.domain.domaincore.valueobject.ProductId;
import org.springframework.stereotype.Component;

@Component
public class ProductCommandHandlerHelper {

    private final ProductRepository productRepository;
    private final AssetRepository assetRepository;

    public ProductCommandHandlerHelper(ProductRepository productRepository,
                                       AssetRepository assetRepository) {
        this.productRepository = productRepository;
        this.assetRepository = assetRepository;
    }

    public Product findProductById(ProductId id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("product %s not found", id.getValue())));
    }

    public Asset findAssetById(AssetId assetId) {
        return assetRepository.findById(assetId.getValue())
                .orElseThrow(() -> new NotFoundException(String.format("asset %s not found", assetId.getValue())));
    }

}
