package com.byakko.service.production.domain.domainapplication.handler.category;

import com.byakko.service.production.domain.domainapplication.dto.category.GetProductCategoryCommand;
import com.byakko.service.production.domain.domainapplication.dto.category.ProductCategoryCommandHandlerHelper;
import com.byakko.service.production.domain.domainapplication.dto.category.ProductCategoryResponse;
import com.byakko.service.production.domain.domainapplication.mapper.ProductCategoryMapper;
import com.byakko.service.production.domain.domaincore.entity.ProductCategory;
import com.byakko.service.production.domain.domaincore.valueobject.ProductCategoryId;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetProductCategoryCommandHandler {

    private final ProductCategoryCommandHandlerHelper productCategoryCommandHandlerHelper;

    public GetProductCategoryCommandHandler(ProductCategoryCommandHandlerHelper productCategoryCommandHandlerHelper) {
        this.productCategoryCommandHandlerHelper = productCategoryCommandHandlerHelper;
    }

    public ProductCategoryResponse handler(GetProductCategoryCommand command) {
        ProductCategory productCategory = productCategoryCommandHandlerHelper.findProductCategoryById(
                new ProductCategoryId(command.getId()),
                command.getDepth()
        );
        return ProductCategoryMapper.toProductCategoryResponse(productCategory);
    }

}
