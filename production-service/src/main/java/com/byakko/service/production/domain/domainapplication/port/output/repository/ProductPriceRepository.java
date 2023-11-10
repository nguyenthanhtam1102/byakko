package com.byakko.service.production.domain.domainapplication.port.output.repository;

import com.byakko.service.production.domain.domainapplication.dto.product.admin.GetPriceHistoriesForProductCommand;
import com.byakko.service.production.domain.domainapplication.dto.product.admin.ProductPriceHistoriesResponse;
import com.byakko.service.production.domain.domaincore.entity.Product;
import com.byakko.service.production.domain.domaincore.entity.ProductPrice;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ProductPriceRepository {

    ProductPriceHistoriesResponse getPricesForProduct(GetPriceHistoriesForProductCommand command);
    Optional<ProductPrice> getLastedPriceForProduct(Product product);
    ProductPrice save(ProductPrice priceHistory);

}
