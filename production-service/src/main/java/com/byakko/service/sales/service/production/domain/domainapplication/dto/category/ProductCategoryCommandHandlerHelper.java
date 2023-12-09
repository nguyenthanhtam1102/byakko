package com.byakko.service.sales.service.production.domain.domainapplication.dto.category;

import com.byakko.service.sales.common.domain.exception.NotFoundException;
import com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository.ProductCategoryRepository;
import com.byakko.service.sales.service.production.domain.domaincore.entity.ProductCategory;
import com.byakko.service.sales.service.production.domain.domaincore.valueobject.ProductCategoryId;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryCommandHandlerHelper {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryCommandHandlerHelper(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public ProductCategory findProductCategoryById(ProductCategoryId id, int depth) {
        return productCategoryRepository.findById(id, depth)
                .orElseThrow(() -> new NotFoundException(String.format("Product category %s not found", id.getValue())));
    }

}
