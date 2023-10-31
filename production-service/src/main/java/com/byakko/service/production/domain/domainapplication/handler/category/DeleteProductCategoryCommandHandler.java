package com.byakko.service.production.domain.domainapplication.handler.category;

import com.byakko.service.production.domain.domainapplication.dto.category.DeleteProductCategoryCommand;
import com.byakko.service.production.domain.domainapplication.dto.category.ProductCategoryCommandHandlerHelper;
import com.byakko.service.production.domain.domainapplication.port.output.repository.ProductCategoryRepository;
import com.byakko.service.production.domain.domaincore.entity.ProductCategory;
import com.byakko.service.production.domain.domaincore.valueobject.ProductCategoryId;
import org.springframework.stereotype.Component;

@Component
public class DeleteProductCategoryCommandHandler {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryCommandHandlerHelper productCategoryCommandHandlerHelper;

    public DeleteProductCategoryCommandHandler(ProductCategoryRepository productCategoryRepository,
                                               ProductCategoryCommandHandlerHelper productCategoryCommandHandlerHelper) {
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryCommandHandlerHelper = productCategoryCommandHandlerHelper;
    }

    public void handler(DeleteProductCategoryCommand command) {
        ProductCategory productCategory = productCategoryCommandHandlerHelper
                .findProductCategoryById(new ProductCategoryId(command.getId()), 0);
        productCategoryRepository.delete(command);
    }

}
