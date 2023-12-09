package com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository;

import com.byakko.service.sales.service.production.domain.domainapplication.dto.category.DeleteProductCategoryCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.category.ListAllProductCategoryCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.category.ListAllProductCategoryResponse;
import com.byakko.service.sales.service.production.domain.domaincore.entity.ProductCategory;
import com.byakko.service.sales.service.production.domain.domaincore.valueobject.ProductCategoryId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ProductCategoryRepository {

    ProductCategory save(ProductCategory category);
    ListAllProductCategoryResponse findAll(ListAllProductCategoryCommand command);
    Optional<ProductCategory> findById(ProductCategoryId id, int depth);
    void delete(DeleteProductCategoryCommand command);

}
