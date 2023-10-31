package com.byakko.service.production.domain.domainapplication.handler.category;

import com.byakko.service.production.domain.domainapplication.dto.category.ListAllProductCategoryCommand;
import com.byakko.service.production.domain.domainapplication.dto.category.ListAllProductCategoryResponse;
import com.byakko.service.production.domain.domainapplication.port.output.repository.ProductCategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class ListAllProductCategoryCommandHandler {

    private final ProductCategoryRepository productCategoryRepository;

    public ListAllProductCategoryCommandHandler(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public ListAllProductCategoryResponse handler(ListAllProductCategoryCommand command) {
        return productCategoryRepository.findAll(command);
    }

}
