package com.byakko.service.product.services.impls;

import com.byakko.common.DomainConstants;
import com.byakko.common.application.dto.ListAllResponse;
import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.common.domain.exception.ValidationException;
import com.byakko.service.product.dtos.category.*;
import com.byakko.service.product.mappers.CategoryMapper;
import com.byakko.service.product.models.Category;
import com.byakko.service.product.repositories.CategoryRepository;
import com.byakko.service.product.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public ListAllCategoriesResponse listAllCategories(ListAllCategoriesCommand command) {
        Sort.Direction direction;
        if(command.getSortDirection() == null || command.getSortDirection().isBlank()) {
            direction = Sort.Direction.DESC;
        } else if(command.getSortDirection().equalsIgnoreCase("DESC")) {
            direction = Sort.Direction.DESC;
        } else if(command.getSortDirection().equalsIgnoreCase("ASC")) {
            direction = Sort.Direction.ASC;
        } else {
            throw new RuntimeException("Sort direction not correct");
        }

        Sort sort;
        if(command.getSortBy() == null || command.getSortBy().isBlank()) {
            sort = Sort.by(direction, "createdAt");
        } else if(command.getSortBy().equalsIgnoreCase("NAME")) {
            sort = Sort.by(direction, "name");
        } else {
            throw new RuntimeException("Sort by not correct");
        }

        Pageable pageable = PageRequest.of(command.getPage(), command.getLimit(), sort);

        Page<Category> page = categoryRepository.findAllByIdOrName(
                "%" + (command.getQuery() != null ? command.getQuery().toLowerCase() : "") + "%",
                pageable
        );

        if (command.getDepth() > 0) {
            page.forEach(category -> loadChildren(category, command.getDepth()));
        }
        return ListAllCategoriesResponse.builder()
                .data(page.stream().map(category -> CategoryMapper.toCategoryResponse(category, command.getDepth())).toList())
                .pagination(ListAllResponse.Pagination.toPagination(page))
                .build();
    }

    @Override
    public ListAllCategoriesResponse getRootCategories(GetRootCategoriesCommand command) {
        Page<Category> rootCategories = categoryRepository.findByParentIsNullAndDeletedIsFalse(PageRequest.of(command.getPage(), command.getLimit()));
        if (command.getDepth() > 0) {
            rootCategories.forEach(category -> loadChildren(category, command.getDepth()));
        }

        return ListAllCategoriesResponse.builder()
                .data(rootCategories.stream().map(category -> CategoryMapper.toCategoryResponse(category, command.getDepth())).toList())
                .pagination(ListAllResponse.Pagination.toPagination(rootCategories))
                .build();
    }

    @Override
    public CategoryResponse getCategory(GetCategoryCommand command) {
        Category category = categoryRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Category with id %s not found", command.getId())));

        loadChildren(category, command.getDepth());

        return CategoryMapper.toCategoryResponse(category, command.getDepth());
    }

    @Override
    public CategoryResponse createCategory(CreateCategoryCommand command) {
        Category category = new Category();
        category.setName(command.getName());
        category.setDescription(command.getDescription());

        if(command.getParent() != null) {
            Category parent = categoryRepository.findById(command.getParent())
                    .orElseThrow(() -> new NotFoundException(String.format("Category parent %s not found", command.getParent())));
            category.setParent(parent);
        }

        categoryRepository.save(category);

        return CategoryMapper.toCategoryResponse(category, 0);
    }

    @Override
    public CategoryResponse updateCategory(UpdateCategoryCommand command) {
        Category category = categoryRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Category with id %s not found", command.getId())));

        if(command.getName() != null) {
            if(command.getName().isBlank())
                throw new ValidationException(Map.of("name", "name must be not blank"));
            category.setName(command.getName());
        }

        if(command.getDescription() != null) {
            category.setDescription(command.getDescription());
        }

        if(command.getParent() != null) {
            if(command.getParent().equals(category.getId()))
                throw new RuntimeException("parent category cannot be itself");

            Category parent = categoryRepository.findById(command.getParent())
                    .orElseThrow(() -> new NotFoundException(String.format("Category parent %s not found", command.getParent())));
            category.setParent(parent);
        }

        category.setUpdatedAt(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)));
        categoryRepository.save(category);

        return CategoryMapper.toCategoryResponse(category, 0);
    }

    @Override
    public void deleteCategory(DeleteCategoryCommand command) {
        Category category = categoryRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Category with id %s not found", command.getId())));

        switch (command.getDeleteOption()) {
            case 0:
                category.getChildren().forEach(child -> child.setParent(null));
                break;
            case 1:
                category.getChildren().forEach(child -> child.setParent(category.getParent()));
                break;
            case 2:
                deleteAllChildCategory(category);
                break;
            default:
                throw new RuntimeException("Delete option not correct");
        }

        category.setDeleted(true);
        categoryRepository.save(category);
    }

    private void loadChildren(Category category, int currentDepth) {
        List<Category> children = categoryRepository.findAllByParentAndDeletedIsFalse(category);
        if (currentDepth <= 0 || children == null || children.isEmpty()) {
            category.setChildren(null);
            return;
        }
        category.setChildren(new HashSet<>(children));
        children.forEach(child -> loadChildren(child, currentDepth - 1));
    }

    private void deleteAllChildCategory(Category category) {
        if(category.getChildren() == null) return;
        category.getChildren().forEach(child -> {
            deleteAllChildCategory(child);
            child.setDeleted(true);
        });
    }

}
