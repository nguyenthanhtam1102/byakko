package com.byakko.service.production.domain.domainapplication.dto.category;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class CreateProductCategoryCommand {

    @NotBlank(message = "name must be not blank")
    private String name;

    private String parent;

    private Set<String> children;

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

    public Set<String> getChildren() {
        return children;
    }

    public void setChildren(Set<String> children) {
        this.children = children;
    }
}
