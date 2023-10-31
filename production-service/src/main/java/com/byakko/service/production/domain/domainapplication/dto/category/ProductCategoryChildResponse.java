package com.byakko.service.production.domain.domainapplication.dto.category;

import java.util.List;

public class ProductCategoryChildResponse {

    private String id;

    private String name;

    private List<ProductCategoryChildResponse> children;

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

    public List<ProductCategoryChildResponse> getChildren() {
        return children;
    }

    public void setChildren(List<ProductCategoryChildResponse> children) {
        this.children = children;
    }


    public static final class Builder {
        private String id;
        private String name;
        private List<ProductCategoryChildResponse> children;

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

        public Builder children(List<ProductCategoryChildResponse> children) {
            this.children = children;
            return this;
        }

        public ProductCategoryChildResponse build() {
            ProductCategoryChildResponse productCategoryChildResponse = new ProductCategoryChildResponse();
            productCategoryChildResponse.setId(id);
            productCategoryChildResponse.setName(name);
            productCategoryChildResponse.setChildren(children);
            return productCategoryChildResponse;
        }
    }
}
