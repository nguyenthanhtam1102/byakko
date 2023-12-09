package com.byakko.service.sales.service.production.domain.domainapplication.handler.product.customer;

import com.byakko.service.sales.common.domain.exception.NotFoundException;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.customer.GetProductDetailCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.customer.ProductDetailResponse;
import com.byakko.service.sales.service.production.domain.domainapplication.handler.product.ProductCommandHandlerHelper;
import com.byakko.service.sales.service.production.domain.domainapplication.mapper.ProductMapper;
import com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository.ProductPriceRepository;
import com.byakko.service.sales.service.production.domain.domaincore.entity.Product;
import com.byakko.service.sales.service.production.domain.domaincore.entity.ProductPrice;
import com.byakko.service.sales.service.production.domain.domaincore.valueobject.ProductId;
import org.springframework.stereotype.Component;

@Component
public class GetProductDetailCommandHandler {

    private final ProductCommandHandlerHelper productCommandHandlerHelper;
    private final ProductPriceRepository productPriceRepository;

    public GetProductDetailCommandHandler(ProductCommandHandlerHelper productCommandHandlerHelper, ProductPriceRepository productPriceRepository) {
        this.productCommandHandlerHelper = productCommandHandlerHelper;
        this.productPriceRepository = productPriceRepository;
    }

    public ProductDetailResponse handler(GetProductDetailCommand command) {
        Product product = productCommandHandlerHelper.findProductById(new ProductId(command.getId()));
        ProductPrice productPrice = productPriceRepository.getLastedPriceForProduct(product)
                .orElseThrow(() -> new NotFoundException(String.format("Not price for product %s", command.getId())));
        return ProductMapper.toProductDetailResponse(product, productPrice);
    }

}
