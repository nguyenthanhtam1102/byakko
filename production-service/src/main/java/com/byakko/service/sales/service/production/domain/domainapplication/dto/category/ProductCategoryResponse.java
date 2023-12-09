package com.byakko.service.sales.service.production.domain.domainapplication.dto.category;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProductCategoryResponse {

    private String id;

    private String name;

    private String parent;

    private List<ProductCategoryChildResponse> children;

    @JsonProperty("created_at")
    private Long createdAt;

    @JsonProperty("updated_at")

    private Long updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<ProductCategoryChildResponse> getChildren() {
        return children;
    }

    public void setChildren(List<ProductCategoryChildResponse> children) {
        this.children = children;
    }

    public static final class Builder {
        private String id;
        private String name;
        private String parent;
        private List<ProductCategoryChildResponse> children;
        private Long createdAt;
        private Long updatedAt;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder parent(String parent) {
            this.parent = parent;
            return this;
        }

        public Builder children(List<ProductCategoryChildResponse> children) {
            this.children = children;
            return this;
        }

        public Builder createdAt(Long createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(Long updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public ProductCategoryResponse build() {
            ProductCategoryResponse productCategoryResponse = new ProductCategoryResponse();
            productCategoryResponse.setId(id);
            productCategoryResponse.setName(name);
            productCategoryResponse.setParent(parent);
            productCategoryResponse.setChildren(children);
            productCategoryResponse.setCreatedAt(createdAt);
            productCategoryResponse.setUpdatedAt(updatedAt);
            return productCategoryResponse;
        }
    }
}
