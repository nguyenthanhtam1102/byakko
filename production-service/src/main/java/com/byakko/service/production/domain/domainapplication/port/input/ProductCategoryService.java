package com.byakko.service.production.domain.domainapplication.port.input;

import com.byakko.service.production.domain.domainapplication.dto.category.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Component
@Validated
public interface ProductCategoryService {

    ListAllProductCategoryResponse listAllProductCategories(@Valid ListAllProductCategoryCommand command);
    ProductCategoryResponse getProductCategory(@Valid GetProductCategoryCommand command);
    ProductCategoryResponse createProductCategory(@Valid CreateProductCategoryCommand command);
    ProductCategoryResponse updateProductCategory(@Valid UpdateProductCategoryCommand command);
    void deleteProductCategory(@Valid DeleteProductCategoryCommand command);

}
