package com.byakko.service.product.dtos.administrative_division;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ListAllDistrictsByProvinceCommand {

    @NotBlank(message = "provinceId must be not blank")
    private String provinceId;

}
