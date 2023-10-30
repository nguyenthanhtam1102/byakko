package com.byakko.service.production.domain.domainapplication.port.input.impl;

import com.byakko.service.production.domain.domainapplication.dto.product.admin.*;
import com.byakko.service.production.domain.domainapplication.dto.product.customer.GetProductDetailCommand;
import com.byakko.service.production.domain.domainapplication.dto.product.customer.ProductDetailResponse;
import com.byakko.service.production.domain.domainapplication.dto.product.customer.ProductFilterCommand;
import com.byakko.service.production.domain.domainapplication.dto.product.customer.ProductFilterResponse;
import com.byakko.service.production.domain.domainapplication.handler.product.admin.*;
import com.byakko.service.production.domain.domainapplication.handler.product.customer.GetProductDetailCommandHandler;
import com.byakko.service.production.domain.domainapplication.handler.product.customer.ProductFilterCommandHandler;
import com.byakko.service.production.domain.domainapplication.port.input.ProductService;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    private final ListAllProductCommandHandler listAllProductCommandHandler;
    private final GetProductCommandHandler getProductCommandHandler;
    private final CreateProductCommandHandler createProductCommandHandler;
    private final UpdateProductCommandHandler updateProductCommandHandler;
    private final DeleteProductCommandHandler deleteProductCommandHandler;
    private final GetPriceHistoryForProductCommandHandler getPriceHistoryForProductCommandHandler;


    private final ProductFilterCommandHandler productFilterCommandHandler;
    private final GetProductDetailCommandHandler getProductDetailCommandHandler;

    public ProductServiceImpl(ListAllProductCommandHandler listAllProductCommandHandler,
                              GetProductCommandHandler getProductCommandHandler,
                              GetProductDetailCommandHandler getProductDetailCommandHandler,
                              CreateProductCommandHandler createProductCommandHandler,
                              UpdateProductCommandHandler updateProductCommandHandler,
                              DeleteProductCommandHandler deleteProductCommandHandler,
                              GetPriceHistoryForProductCommandHandler getPriceHistoryForProductCommandHandler,
                              ProductFilterCommandHandler productFilterCommandHandler) {
        this.listAllProductCommandHandler = listAllProductCommandHandler;
        this.getProductCommandHandler = getProductCommandHandler;
        this.getProductDetailCommandHandler = getProductDetailCommandHandler;
        this.createProductCommandHandler = createProductCommandHandler;
        this.updateProductCommandHandler = updateProductCommandHandler;
        this.deleteProductCommandHandler = deleteProductCommandHandler;
        this.getPriceHistoryForProductCommandHandler = getPriceHistoryForProductCommandHandler;
        this.productFilterCommandHandler = productFilterCommandHandler;
    }

    @Override
    public ListAllProductResponse listAllProducts(ListAllProductCommand command) {
        return listAllProductCommandHandler.handler(command);
    }

    @Override
    public ProductResponse getProduct(GetProductCommand command) {
        return getProductCommandHandler.handler(command);
    }

    @Override
    public ProductDetailResponse getProductDetail(GetProductDetailCommand command) {
        return getProductDetailCommandHandler.handler(command);
    }

    @Override
    public ProductResponse createProduct(CreateProductCommand command) {
        return createProductCommandHandler.handler(command);
    }

    @Override
    public ProductResponse updateProduct(UpdateProductCommand command) {
        return updateProductCommandHandler.handler(command);
    }

    @Override
    public void deleteProduct(DeleteProductCommand command) {
        deleteProductCommandHandler.handler(command);
    }

    @Override
    public ProductPriceHistoriesResponse getPriceHistoriesForProduct(GetPriceHistoriesForProductCommand command) {
        return getPriceHistoryForProductCommandHandler.handler(command);
    }

    @Override
    public ProductFilterResponse filterProduct(ProductFilterCommand command) {
        return productFilterCommandHandler.handler(command);
    }
}
