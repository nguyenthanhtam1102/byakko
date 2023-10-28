package com.byakko.service.production.domain.domainapplication.port.input;

import com.byakko.service.production.domain.domainapplication.dto.global_option.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Component
@Validated
public interface GlobalOptionService {

    ListAllGlobalOptionResponse listAllGlobalOption(@Valid ListAllGlobalOptionCommand command);
    OptionResponse getGlobalOption(@Valid GetGlobalOptionCommand command);
    OptionResponse createGlobalOption(@Valid CreateGlobalOptionCommand command);
    OptionResponse updateGlobalOption(@Valid UpdateGlobalOptionCommand command);
    void deleteGlobalOption(@Valid DeleteGlobalOptionCommand command);

}
