package com.byakko.service.production.domain.domainapplication.handler.product.admin;

import com.byakko.service.production.domain.domainapplication.dto.product.admin.GetProductCommand;
import com.byakko.service.production.domain.domainapplication.dto.product.admin.ProductResponse;
import com.byakko.service.production.domain.domainapplication.handler.product.ProductCommandHandlerHelper;
import com.byakko.service.production.domain.domainapplication.mapper.ProductMapper;
import com.byakko.service.production.domain.domaincore.entity.Product;
import com.byakko.service.production.domain.domaincore.valueobject.ProductId;
import org.springframework.stereotype.Component;

@Component
public class GetProductCommandHandler {

    private final ProductCommandHandlerHelper productCommandHandlerHelper;

    public GetProductCommandHandler(ProductCommandHandlerHelper productCommandHandlerHelper) {
        this.productCommandHandlerHelper = productCommandHandlerHelper;
    }

    public ProductResponse handler(GetProductCommand command) {
        Product product = productCommandHandlerHelper.findProductById(new ProductId(command.getId()));
        return ProductMapper.toProductResponse(product);
    }

}
