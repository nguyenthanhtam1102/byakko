package com.byakko.service.product.services.impls;

import com.byakko.service.product.dtos.administrative_division.AdministrativeDivisionResponse;
import com.byakko.service.product.dtos.administrative_division.ListAllCommunesByDistrictCommand;
import com.byakko.service.product.dtos.administrative_division.ListAllDistrictsByProvinceCommand;
import com.byakko.service.product.dtos.administrative_division.ListAllProvincesCommand;
import com.byakko.service.product.mappers.AdministrativeDivisionMapper;
import com.byakko.service.product.repositories.AdministrativeDivisionRepository;
import com.byakko.service.product.services.AdministrativeDivisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AdministrativeDivisionServiceImpl implements AdministrativeDivisionService {

    private final AdministrativeDivisionRepository administrativeDivisionRepository;

    @Override
    public List<AdministrativeDivisionResponse> listAllProvinces(ListAllProvincesCommand command) {
        return administrativeDivisionRepository.listAllProvinces()
                .stream()
                .map(AdministrativeDivisionMapper::toAdministrativeDivisionResponse)
                .toList();
    }

    @Override
    public List<AdministrativeDivisionResponse> listAllDistrictsByProvince(ListAllDistrictsByProvinceCommand command) {
        return administrativeDivisionRepository.listAllDistrictsByProvince(command.getProvinceId())
                .stream()
                .map(AdministrativeDivisionMapper::toAdministrativeDivisionResponse)
                .toList();
    }

    @Override
    public List<AdministrativeDivisionResponse> listAllCommunesByDistrict(ListAllCommunesByDistrictCommand command) {
        return administrativeDivisionRepository.listAllCommunesByDistrict(command.getDistrictId())
                .stream()
                .map(AdministrativeDivisionMapper::toAdministrativeDivisionResponse)
                .toList();
    }

}
