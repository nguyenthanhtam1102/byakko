package com.byakko.service.sales.service.product.services;

import com.byakko.service.product.dtos.product_price.*;
import com.byakko.service.sales.service.product.dtos.product_price.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public interface ProductPriceService {

    ListProductPricesResponse listProductPrices(@Valid ListProductPricesCommand command);
    ProductPriceResponse createProductPrice(@Valid CreateProductPriceCommand command);
    ProductPriceResponse updateProductPrice(@Valid UpdateProductPriceCommand command);
    void deleteProductPrice(@Valid DeleteProductPriceCommand command);

}
