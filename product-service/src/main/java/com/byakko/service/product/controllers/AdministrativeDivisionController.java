package com.byakko.service.product.controllers;

import com.byakko.service.product.dtos.administrative_division.ListAllCommunesByDistrictCommand;
import com.byakko.service.product.dtos.administrative_division.ListAllDistrictsByProvinceCommand;
import com.byakko.service.product.dtos.administrative_division.ListAllProvincesCommand;
import com.byakko.service.product.services.AdministrativeDivisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdministrativeDivisionController {

    private final AdministrativeDivisionService administrativeDivisionService;

    @GetMapping("/provinces")
    public ResponseEntity<?> listAllProvinces(@ModelAttribute ListAllProvincesCommand command) {
        return ResponseEntity.ok(administrativeDivisionService.listAllProvinces(command));
    }

    @GetMapping("/provinces/{id}/districts")
    public ResponseEntity<?> listAllDistrictsByProvince(@PathVariable("id") String provinceId) {
        return ResponseEntity.ok(administrativeDivisionService.listAllDistrictsByProvince(new ListAllDistrictsByProvinceCommand(provinceId)));
    }

    @GetMapping("/districts/{id}/communes")
    public ResponseEntity<?> listAllCommunesByDistrict(@PathVariable("id") String districtId) {
        return ResponseEntity.ok(administrativeDivisionService.listAllCommunesByDistrict(new ListAllCommunesByDistrictCommand(districtId)));
    }

}
