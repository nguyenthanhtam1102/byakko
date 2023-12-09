package com.byakko.service.sales.service.production.dataaccess.adapter;

import com.byakko.service.sales.common.application.dto.ListAllResponse;
import com.byakko.service.sales.common.domain.exception.NotFoundException;
import com.byakko.service.sales.service.production.dataaccess.entity.ProductCategoryEntity;
import com.byakko.service.sales.service.production.dataaccess.mapper.ProductCategoryMapper;
import com.byakko.service.sales.service.production.dataaccess.repository.ProductCategoryJpaRepository;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.category.DeleteProductCategoryCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.category.ListAllProductCategoryCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.category.ListAllProductCategoryResponse;
import com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository.ProductCategoryRepository;
import com.byakko.service.sales.service.production.domain.domaincore.entity.ProductCategory;
import com.byakko.service.sales.service.production.domain.domaincore.valueobject.ProductCategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ProductCategoryRepositoryImpl implements ProductCategoryRepository {

    private final ProductCategoryJpaRepository productCategoryJpaRepository;

    @Override
    public ProductCategory save(ProductCategory category) {
        productCategoryJpaRepository.save(ProductCategoryMapper.toProductCategoryEntity(category));
        return category;
    }

    @Override
    public ListAllProductCategoryResponse findAll(ListAllProductCategoryCommand command) {
        Pageable pageable = PageRequest.of(
                command.getPage(),
                command.getLimit(),
                Sort.by(Sort.Direction.DESC, "createdAt")
        );

        Page<ProductCategoryEntity> page = productCategoryJpaRepository.findAll(pageable);

        return ListAllProductCategoryResponse.builder()
                .data(page.get().map(productCategoryEntity -> {
                    productCategoryEntity.setChildren(getChildrenForProductCategory(productCategoryEntity, command.getDepth()));
                    return ProductCategoryMapper.toProductCategoryResponse(productCategoryEntity);
                }).toList())
                .pagination(ListAllResponse.Pagination.toPagination(page))
                .build();
    }

    @Override
    public Optional<ProductCategory> findById(ProductCategoryId id, int depth) {
        Optional<ProductCategoryEntity> productCategoryEntity = productCategoryJpaRepository.findById(id.getValue());
        productCategoryEntity.ifPresent(productCategory -> productCategory.setChildren(getChildrenForProductCategory(productCategory, depth)));
        return productCategoryEntity.map(ProductCategoryMapper::toProductCategory);
    }

    @Transactional
    @Override
    public void delete(DeleteProductCategoryCommand command) {
        ProductCategoryEntity productCategoryEntity = productCategoryJpaRepository.findById(command.getId()).
                orElseThrow(() -> new NotFoundException(String.format("Category %s not found", command.getId())));

        List<ProductCategoryEntity> children = productCategoryJpaRepository.findByParent(productCategoryEntity);

        switch (command.getHandleChild()) {
            case 0:
                children.forEach(child -> child.setParent(null));
                productCategoryJpaRepository.saveAllAndFlush(productCategoryEntity.getChildren());
                break;
            case 1:
                children.forEach(child -> child.setParent(productCategoryEntity.getParent()));
                productCategoryJpaRepository.saveAllAndFlush(productCategoryEntity.getChildren());
                break;
            case 2:
                deleteAllChildrenForProductCategory(productCategoryEntity);
                break;
            default:
                throw new RuntimeException("handlerChild must be equal 0 or 1 or 2");
        }

        productCategoryJpaRepository.delete(new ProductCategoryEntity(command.getId()));
    }

    private Set<ProductCategoryEntity> getChildrenForProductCategory(ProductCategoryEntity productCategoryEntity, int depth) {
        if(depth <= 0) return new HashSet<>();

        for(ProductCategoryEntity child : productCategoryEntity.getChildren()) {
            child.setChildren(getChildrenForProductCategory(child, --depth));
        }

        return productCategoryEntity.getChildren();
    }

    private void deleteAllChildrenForProductCategory(ProductCategoryEntity productCategoryEntity) {
        for(ProductCategoryEntity child : productCategoryJpaRepository.findByParent(productCategoryEntity)) {
            deleteAllChildrenForProductCategory(child);
            productCategoryJpaRepository.delete(child);
            productCategoryJpaRepository.flush();
        }
    }

}
