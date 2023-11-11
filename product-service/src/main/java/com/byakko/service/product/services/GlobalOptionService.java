package com.byakko.service.product.services;

import com.byakko.service.product.dtos.global_option.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Validated
public interface GlobalOptionService {

    ListAllOptionsResponse listAllOptions(@Valid ListAllOptionsCommand command);
    OptionResponse getOption(@Valid GetOptionCommand command);
    OptionResponse createOption(@Valid CreateOptionCommand command);
    OptionResponse updateOption(@Valid UpdateOptionCommand command);
    void deleteOption(@Valid DeleteOptionCommand command);

}
