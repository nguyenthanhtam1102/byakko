package com.byakko.service.sales.service.production.domain.domainapplication.handler.global_option;

import com.byakko.service.sales.service.production.domain.domainapplication.dto.global_option.GetGlobalOptionCommand;
import com.byakko.service.sales.service.production.domain.domainapplication.dto.global_option.OptionResponse;
import com.byakko.service.sales.service.production.domain.domainapplication.mapper.OptionMapper;
import com.byakko.service.sales.service.production.domain.domaincore.entity.GlobalOption;
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
