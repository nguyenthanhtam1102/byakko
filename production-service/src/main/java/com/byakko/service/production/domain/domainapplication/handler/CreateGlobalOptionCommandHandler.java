package com.byakko.service.production.domain.domainapplication.handler;

import com.byakko.service.production.domain.domainapplication.dto.CreateGlobalOptionCommand;
import com.byakko.service.production.domain.domainapplication.dto.OptionResponse;
import com.byakko.service.production.domain.domainapplication.mapper.OptionMapper;
import com.byakko.service.production.domain.domainapplication.port.output.repository.GlobalOptionRepository;
import com.byakko.service.production.domain.domaincore.entity.GlobalOption;
import com.byakko.service.production.domain.domaincore.entity.GlobalOptionValue;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Component
public class CreateGlobalOptionCommandHandler {

    private final GlobalOptionRepository globalOptionRepository;

    public CreateGlobalOptionCommandHandler(GlobalOptionRepository globalOptionRepository) {
        this.globalOptionRepository = globalOptionRepository;
    }

    @Transactional
    public OptionResponse handler(CreateGlobalOptionCommand command) {
        GlobalOption option = GlobalOption.Builder.builder()
                .name(command.getName())
                .build();

        if(command.getValues() != null) {
            option.setValues(
                    command.getValues()
                            .stream()
                            .map(valueName -> new GlobalOptionValue(valueName, option))
                            .collect(Collectors.toSet())
            );
        }

        option.initialize();
        option.validate();

        globalOptionRepository.save(option);

        return OptionMapper.toOptionResponse(option);
    }

}
