package com.byakko.service.sales.service.product.services;

import com.byakko.service.product.dtos.category.*;
import com.byakko.service.sales.service.product.dtos.category.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public interface CategoryService {

    ListAllCategoriesResponse listAllCategories(@Valid ListAllCategoriesCommand command);
    ListAllCategoriesResponse getRootCategories(@Valid GetRootCategoriesCommand command);
    CategoryResponse getCategory(@Valid GetCategoryCommand command);
    CategoryResponse createCategory(@Valid CreateCategoryCommand command);
    CategoryResponse updateCategory(@Valid UpdateCategoryCommand command);
    void deleteCategory(@Valid DeleteCategoryCommand command);

}
