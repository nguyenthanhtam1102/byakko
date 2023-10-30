package com.byakko.service.production.domain.domainapplication.handler.product.customer;

import com.byakko.service.production.domain.domainapplication.dto.product.customer.GetProductDetailCommand;
import com.byakko.service.production.domain.domainapplication.dto.product.customer.ProductDetailResponse;
import com.byakko.service.production.domain.domainapplication.handler.product.ProductCommandHandlerHelper;
import com.byakko.service.production.domain.domainapplication.mapper.ProductMapper;
import com.byakko.service.production.domain.domaincore.entity.Product;
import com.byakko.service.production.domain.domaincore.valueobject.ProductId;
import org.springframework.stereotype.Component;

@Component
public class GetProductDetailCommandHandler {

    private final ProductCommandHandlerHelper productCommandHandlerHelper;

    public GetProductDetailCommandHandler(ProductCommandHandlerHelper productCommandHandlerHelper) {
        this.productCommandHandlerHelper = productCommandHandlerHelper;
    }

    public ProductDetailResponse handler(GetProductDetailCommand command) {
        Product product = productCommandHandlerHelper.findProductById(new ProductId(command.getId()));
        return ProductMapper.toProductDetailResponse(product);
    }

}
