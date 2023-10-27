package com.byakko.service.production.domain.domainapplication.handler;

import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.service.production.domain.domainapplication.port.output.repository.GlobalOptionRepository;
import com.byakko.service.production.domain.domaincore.entity.GlobalOption;
import org.springframework.stereotype.Component;

@Component
public class GlobalOptionCommandHandlerHelper {

    private final GlobalOptionRepository globalOptionRepository;

    public GlobalOptionCommandHandlerHelper(GlobalOptionRepository globalOptionRepository) {
        this.globalOptionRepository = globalOptionRepository;
    }

    public GlobalOption findOptionById(String id) {
        return globalOptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Option %s not found", id)));
    }

}