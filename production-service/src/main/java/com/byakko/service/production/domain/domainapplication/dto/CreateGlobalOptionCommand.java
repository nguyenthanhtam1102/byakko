package com.byakko.service.production.domain.domainapplication.dto;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class CreateGlobalOptionCommand {

    @NotBlank(message = "name must be not blank")
    private String name;

    private Set<String> values;

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
