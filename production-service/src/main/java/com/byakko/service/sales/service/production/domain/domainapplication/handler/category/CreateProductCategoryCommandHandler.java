package com.byakko.service.sales.service.production.domain.domainapplication.handler.category;

import com.byakko.service.sales.service.production.domain.domainapplication.dto.category.CreateProductCategoryCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.category.ProductCategoryCommandHandlerHelper;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.category.ProductCategoryResponse;
import com.byakko.service.sales.service.production.domain.domainapplication.mapper.ProductCategoryMapper;
import com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository.ProductCategoryRepository;
import com.byakko.service.sales.service.production.domain.domaincore.entity.ProductCategory;
import com.byakko.service.sales.service.production.domain.domaincore.valueobject.ProductCategoryId;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CreateProductCategoryCommandHandler {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryCommandHandlerHelper productCategoryCommandHandlerHelper;

    public CreateProductCategoryCommandHandler(ProductCategoryRepository productCategoryRepository,
                                               ProductCategoryCommandHandlerHelper productCategoryCommandHandlerHelper) {
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryCommandHandlerHelper = productCategoryCommandHandlerHelper;
    }

    public ProductCategoryResponse handler(CreateProductCategoryCommand command) {
        ProductCategory productCategory = ProductCategory.Builder.builder()
                .name(command.getName())
                .build();

        if(command.getParent() != null) {
            ProductCategory parent = productCategoryCommandHandlerHelper.findProductCategoryById(new ProductCategoryId(command.getParent()), 0);
            productCategory.setParent(parent);
        }

        if(command.getChildren() != null && !command.getChildren().isEmpty()) {
            Set<ProductCategory> children = command.getChildren()
                    .stream()
                    .map(childId -> productCategoryCommandHandlerHelper.findProductCategoryById(new ProductCategoryId(childId), 0))
                    .collect(Collectors.toSet());
            productCategory.setChildren(children);
        }

        productCategory.initialize();
        productCategory.validate();

        productCategoryRepository.save(productCategory);

        return ProductCategoryMapper.toProductCategoryResponse(productCategory);
    }

}
