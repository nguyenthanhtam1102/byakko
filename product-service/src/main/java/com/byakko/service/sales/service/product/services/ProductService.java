package com.byakko.service.sales.service.product.services;

import com.byakko.service.product.dtos.product.*;
import com.byakko.service.sales.service.product.dtos.product.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public interface ProductService {

    ListAllProductsResponse listAllProducts(@Valid ListAllProductsCommand command);
    ProductResponse getProduct(@Valid GetProductCommand command);
    ProductResponse createProduct(@Valid CreateProductCommand command);
    ProductResponse updateProduct(@Valid UpdateProductCommand command);
    void deleteProduct(@Valid DeleteProductCommand command);

}
