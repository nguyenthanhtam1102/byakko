package com.byakko.service.production.domain.domainapplication.handler;

import com.byakko.service.production.domain.domainapplication.dto.GetGlobalOptionCommand;
import com.byakko.service.production.domain.domainapplication.dto.OptionResponse;
import com.byakko.service.production.domain.domainapplication.mapper.OptionMapper;
import com.byakko.service.production.domain.domaincore.entity.GlobalOption;
import org.springframework.stereotype.Component;

@Component
public class GetGlobalOptionCommandHandler {

    private final GlobalOptionCommandHandlerHelper globalOptionCommandHandlerHelper;

    public GetGlobalOptionCommandHandler(GlobalOptionCommandHandlerHelper globalOptionCommandHandlerHelper) {
        this.globalOptionCommandHandlerHelper = globalOptionCommandHandlerHelper;
    }

    public OptionResponse handler(GetGlobalOptionCommand command) {
        GlobalOption option = globalOptionCommandHandlerHelper.findOptionById(command.getId());
        return OptionMapper.toOptionResponse(option);
    }

}
