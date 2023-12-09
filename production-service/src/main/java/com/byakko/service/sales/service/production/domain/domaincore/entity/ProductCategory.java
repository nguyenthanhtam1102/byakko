package com.byakko.service.sales.service.production.domain.domaincore.entity;

import com.byakko.service.sales.common.DomainConstants;
import com.byakko.service.sales.common.domain.entity.BaseEntity;
import com.byakko.service.sales.common.domain.exception.ValidationException;
import com.byakko.service.sales.service.production.domain.domaincore.valueobject.ProductCategoryId;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class ProductCategory extends BaseEntity<ProductCategoryId> {

    private String name;

    private ProductCategory parent;

    private Set<ProductCategory> children;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    public ProductCategory() {
    }

    public ProductCategory(ProductCategoryId productCategoryId) {
        super(productCategoryId);
    }

    public void initialize() {
        if(getId() == null) {
            setId(new ProductCategoryId(UUID.randomUUID().toString()));
        }
        if(createdAt == null) {
            createdAt = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID));
        }
    }

    public void validate() {
        validateName();
        validateCreatedAt();
    }

    public void validateName() {
        if(name == null || name.isBlank()) {
            throw new ValidationException(Map.of("name", "name must be not blank"));
        }
    }

    public void validateCreatedAt() {
        if(createdAt == null) {
            throw new ValidationException(Map.of("createdAt", "createdAt must be not null"));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getParent() {
        return parent;
    }

    public void setParent(ProductCategory parent) {
        this.parent = parent;
    }

    public Set<ProductCategory> getChildren() {
        return children;
    }

    public void setChildren(Set<ProductCategory> children) {
        this.children = children;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static final class Builder {
        private ProductCategoryId id;
        private String name;
        private ProductCategory parent;
        private Set<ProductCategory> children;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(ProductCategoryId id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder parent(ProductCategory parent) {
            this.parent = parent;
            return this;
        }

        public Builder children(Set<ProductCategory> children) {
            this.children = children;
            return this;
        }

        public Builder createdAt(ZonedDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(ZonedDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public ProductCategory build() {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setId(id);
            productCategory.setName(name);
            productCategory.setParent(parent);
            productCategory.setChildren(children);
            productCategory.setCreatedAt(createdAt);
            productCategory.setUpdatedAt(updatedAt);
            return productCategory;
        }
    }
}
