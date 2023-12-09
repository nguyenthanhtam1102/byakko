package com.byakko.service.sales.service.production.domain.domainapplication.dto.global_option;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class UpdateGlobalOptionCommand {

    @NotBlank(message = "id must be not blank")
    private String id;

    private String name;

    private Set<String> values;

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

    public Set<String> getValues() {
        return values;
    }

    public void setValues(Set<String> values) {
        this.values = values;
    }

}
