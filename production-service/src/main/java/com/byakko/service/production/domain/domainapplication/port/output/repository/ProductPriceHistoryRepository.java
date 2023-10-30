package com.byakko.service.production.domain.domainapplication.port.output.repository;

import com.byakko.service.production.domain.domainapplication.dto.product.admin.GetPriceHistoriesForProductCommand;
import com.byakko.service.production.domain.domainapplication.dto.product.admin.ProductPriceHistoriesResponse;
import com.byakko.service.production.domain.domaincore.entity.Product;
import com.byakko.service.production.domain.domaincore.entity.ProductPriceHistory;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ProductPriceHistoryRepository {

    ProductPriceHistoriesResponse getPriceHistoriesForProduct(GetPriceHistoriesForProductCommand command);
    Optional<ProductPriceHistory> getLastedPriceForProduct(Product product);
    ProductPriceHistory save(ProductPriceHistory priceHistory);

}
