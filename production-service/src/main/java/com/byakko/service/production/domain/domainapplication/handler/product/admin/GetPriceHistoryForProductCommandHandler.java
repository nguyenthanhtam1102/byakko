package com.byakko.service.production.domain.domainapplication.handler.product.admin;

import com.byakko.service.production.domain.domainapplication.dto.product.admin.GetPriceHistoriesForProductCommand;
import com.byakko.service.production.domain.domainapplication.dto.product.admin.ProductPriceHistoriesResponse;
import com.byakko.service.production.domain.domainapplication.handler.product.ProductCommandHandlerHelper;
import com.byakko.service.production.domain.domainapplication.port.output.repository.ProductPriceRepository;
import com.byakko.service.production.domain.domaincore.valueobject.ProductId;
import org.springframework.stereotype.Component;

@Component
public class GetPriceHistoryForProductCommandHandler {

    private final ProductCommandHandlerHelper productCommandHandlerHelper;
    private final ProductPriceRepository productPriceRepository;

    public GetPriceHistoryForProductCommandHandler(ProductCommandHandlerHelper productCommandHandlerHelper,
                                                   ProductPriceRepository productPriceRepository) {
        this.productCommandHandlerHelper = productCommandHandlerHelper;
        this.productPriceRepository = productPriceRepository;
    }

    public ProductPriceHistoriesResponse handler(GetPriceHistoriesForProductCommand command) {
        productCommandHandlerHelper.findProductById(new ProductId(command.getId()));
        return productPriceRepository.getPricesForProduct(command);
    }

}
