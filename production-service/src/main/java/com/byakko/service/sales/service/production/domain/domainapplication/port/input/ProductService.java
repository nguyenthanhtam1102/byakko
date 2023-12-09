package com.byakko.service.sales.service.production.domain.domainapplication.port.input;

import com.byakko.service.production.domain.domainapplication.dto.product.admin.*;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.admin.*;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.customer.GetProductDetailCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.customer.ProductDetailResponse;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.customer.ProductFilterCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.product.customer.ProductFilterResponse;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Component
@Validated
public interface ProductService {

    //    ADMIN
    ListAllProductResponse listAllProducts(@Valid ListAllProductCommand command);
    ProductResponse getProduct(@Valid GetProductCommand command);
    ProductResponse createProduct(@Valid CreateProductCommand command);
    ProductResponse updateProduct(@Valid UpdateProductCommand command);
    void deleteProduct(@Valid DeleteProductCommand command);
    ProductPriceHistoriesResponse getPriceHistoriesForProduct(@Valid GetPriceHistoriesForProductCommand command);

    //    CUSTOMER
    ProductFilterResponse filterProduct(@Valid ProductFilterCommand command);
    ProductDetailResponse getProductDetail(@Valid GetProductDetailCommand command);


}
