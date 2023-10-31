package com.byakko.service.production.domain.domainapplication.handler.category;

import com.byakko.common.DomainConstants;
import com.byakko.service.production.domain.domainapplication.dto.category.ProductCategoryCommandHandlerHelper;
import com.byakko.service.production.domain.domainapplication.dto.category.ProductCategoryResponse;
import com.byakko.service.production.domain.domainapplication.dto.category.UpdateProductCategoryCommand;
import com.byakko.service.production.domain.domainapplication.mapper.ProductCategoryMapper;
import com.byakko.service.production.domain.domainapplication.port.output.repository.ProductCategoryRepository;
import com.byakko.service.production.domain.domaincore.entity.ProductCategory;
import com.byakko.service.production.domain.domaincore.valueobject.ProductCategoryId;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UpdateProductCategoryCommandHandler {

    private final ProductCategoryCommandHandlerHelper productCategoryCommandHandlerHelper;
    private final ProductCategoryRepository productCategoryRepository;

    public UpdateProductCategoryCommandHandler(ProductCategoryCommandHandlerHelper productCategoryCommandHandlerHelper,
                                               ProductCategoryRepository productCategoryRepository) {
        this.productCategoryCommandHandlerHelper = productCategoryCommandHandlerHelper;
        this.productCategoryRepository = productCategoryRepository;
    }

    public ProductCategoryResponse handler(UpdateProductCategoryCommand command) {
        ProductCategory productCategory = productCategoryCommandHandlerHelper.findProductCategoryById(new ProductCategoryId(command.getId()), 0);

        if(command.getName() != null) {
            productCategory.setName(command.getName());
        }

        if(command.getParent() != null) {
            ProductCategory parent = productCategoryCommandHandlerHelper.findProductCategoryById(new ProductCategoryId(command.getParent()), 0);
            productCategory.setParent(parent);
        }

        if(command.getChildren() != null) {
            Set<ProductCategory> children = command.getChildren()
                    .stream()
                    .map(childId -> {
                        ProductCategory childCategory = productCategoryCommandHandlerHelper.findProductCategoryById(new ProductCategoryId(childId), 0);
                        childCategory.setParent(productCategory);
                        return childCategory;
                    })
                    .collect(Collectors.toSet());
            productCategory.setChildren(children);
        }

        productCategory.setUpdatedAt(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)));
        productCategory.validate();

        productCategoryRepository.save(productCategory);

        return ProductCategoryMapper.toProductCategoryResponse(productCategory);
    }

}
