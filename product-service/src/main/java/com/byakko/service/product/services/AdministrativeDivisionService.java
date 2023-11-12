package com.byakko.service.product.services;

import com.byakko.service.product.dtos.administrative_division.AdministrativeDivisionResponse;
import com.byakko.service.product.dtos.administrative_division.ListAllCommunesByDistrictCommand;
import com.byakko.service.product.dtos.administrative_division.ListAllDistrictsByProvinceCommand;
import com.byakko.service.product.dtos.administrative_division.ListAllProvincesCommand;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public interface AdministrativeDivisionService {

    List<AdministrativeDivisionResponse> listAllProvinces(@Valid ListAllProvincesCommand command);
    List<AdministrativeDivisionResponse> listAllDistrictsByProvince(@Valid ListAllDistrictsByProvinceCommand command);
    List<AdministrativeDivisionResponse> listAllCommunesByDistrict(@Valid ListAllCommunesByDistrictCommand command);

}
