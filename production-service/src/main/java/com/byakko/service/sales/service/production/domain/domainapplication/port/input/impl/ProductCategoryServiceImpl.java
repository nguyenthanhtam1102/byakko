package com.byakko.service.sales.service.production.domain.domainapplication.port.input.impl;

import com.byakko.service.production.domain.domainapplication.dto.category.*;
import com.byakko.service.production.domain.domainapplication.handler.category.*;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.category.*;
import com.byakko.service.sales.service.production.domain.domainapplication.handler.category.*;
import com.byakko.service.sales.service.production.domain.domainapplication.port.input.ProductCategoryService;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ListAllProductCategoryCommandHandler listAllProductCategoryCommandHandler;
    private final GetProductCategoryCommandHandler getProductCategoryCommandHandler;
    private final CreateProductCategoryCommandHandler createProductCategoryCommandHandler;
    private final UpdateProductCategoryCommandHandler updateProductCategoryCommandHandler;
    private final DeleteProductCategoryCommandHandler deleteProductCategoryCommandHandler;

    public ProductCategoryServiceImpl(ListAllProductCategoryCommandHandler listAllProductCategoryCommandHandler,
                                      GetProductCategoryCommandHandler getProductCategoryCommandHandler,
                                      CreateProductCategoryCommandHandler createProductCategoryCommandHandler,
                                      UpdateProductCategoryCommandHandler updateProductCategoryCommandHandler,
                                      DeleteProductCategoryCommandHandler deleteProductCategoryCommandHandler) {
        this.listAllProductCategoryCommandHandler = listAllProductCategoryCommandHandler;
        this.getProductCategoryCommandHandler = getProductCategoryCommandHandler;
        this.createProductCategoryCommandHandler = createProductCategoryCommandHandler;
        this.updateProductCategoryCommandHandler = updateProductCategoryCommandHandler;
        this.deleteProductCategoryCommandHandler = deleteProductCategoryCommandHandler;
    }

    @Override
    public ListAllProductCategoryResponse listAllProductCategories(ListAllProductCategoryCommand command) {
        return listAllProductCategoryCommandHandler.handler(command);
    }

    @Override
    public ProductCategoryResponse getProductCategory(GetProductCategoryCommand command) {
        return getProductCategoryCommandHandler.handler(command);
    }

    @Override
    public ProductCategoryResponse createProductCategory(CreateProductCategoryCommand command) {
        return createProductCategoryCommandHandler.handler(command);
    }

    @Override
    public ProductCategoryResponse updateProductCategory(UpdateProductCategoryCommand command) {
        return updateProductCategoryCommandHandler.handler(command);
    }

    @Override
    public void deleteProductCategory(DeleteProductCategoryCommand command) {
        deleteProductCategoryCommandHandler.handler(command);
    }
}
