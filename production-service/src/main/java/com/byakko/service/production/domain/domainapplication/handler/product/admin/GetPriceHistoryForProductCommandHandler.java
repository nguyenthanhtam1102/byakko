package com.byakko.service.production.domain.domainapplication.handler.product.admin;

import com.byakko.service.production.domain.domainapplication.dto.product.admin.GetPriceHistoriesForProductCommand;
import com.byakko.service.production.domain.domainapplication.dto.product.admin.ProductPriceHistoriesResponse;
import com.byakko.service.production.domain.domainapplication.handler.product.ProductCommandHandlerHelper;
import com.byakko.service.production.domain.domainapplication.port.output.repository.ProductPriceHistoryRepository;
import com.byakko.service.production.domain.domaincore.valueobject.ProductId;
import org.springframework.stereotype.Component;

@Component
public class GetPriceHistoryForProductCommandHandler {

    private final ProductCommandHandlerHelper productCommandHandlerHelper;
    private final ProductPriceHistoryRepository productPriceHistoryRepository;

    public GetPriceHistoryForProductCommandHandler(ProductCommandHandlerHelper productCommandHandlerHelper,
                                                   ProductPriceHistoryRepository productPriceHistoryRepository) {
        this.productCommandHandlerHelper = productCommandHandlerHelper;
        this.productPriceHistoryRepository = productPriceHistoryRepository;
    }

    public ProductPriceHistoriesResponse handler(GetPriceHistoriesForProductCommand command) {
        productCommandHandlerHelper.findProductById(new ProductId(command.getId()));
        return productPriceHistoryRepository.getPriceHistoriesForProduct(command);
    }

}
