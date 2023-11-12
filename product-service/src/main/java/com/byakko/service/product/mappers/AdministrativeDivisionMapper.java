package com.byakko.service.product.mappers;

import com.byakko.service.product.dtos.administrative_division.AdministrativeDivisionResponse;
import com.byakko.service.product.models.AdministrativeDivision;

public class AdministrativeDivisionMapper {

    public static AdministrativeDivisionResponse toAdministrativeDivisionResponse(AdministrativeDivision division) {
        return AdministrativeDivisionResponse.builder()
                .code(division.getCode())
                .name(division.getName())
                .nameWithType(division.getNameWithType())
                .path(division.getPath())
                .pathWithType(division.getPathWithType())
                .slug(division.getSlug())
                .build();
    }

}
