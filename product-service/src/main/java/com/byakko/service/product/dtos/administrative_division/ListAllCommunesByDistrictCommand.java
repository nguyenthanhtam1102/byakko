package com.byakko.service.product.dtos.administrative_division;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ListAllCommunesByDistrictCommand {

    @NotBlank(message = "districtId must be not blank")
    private String districtId;

}
