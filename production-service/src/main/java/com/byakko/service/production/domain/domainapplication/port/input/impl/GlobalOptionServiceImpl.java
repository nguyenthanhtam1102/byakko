package com.byakko.service.production.domain.domainapplication.port.input.impl;

import com.byakko.service.production.domain.domainapplication.dto.global_option.*;
import com.byakko.service.production.domain.domainapplication.handler.global_option.*;
import com.byakko.service.production.domain.domainapplication.port.input.GlobalOptionService;
import org.springframework.stereotype.Component;

@Component
public class GlobalOptionServiceImpl implements GlobalOptionService {

    private final ListAllGlobalOptionCommandHandler listAllGlobalOptionCommandHandler;
    private final GetGlobalOptionCommandHandler getGlobalOptionCommandHandler;
    private final CreateGlobalOptionCommandHandler createGlobalOptionCommandHandler;
    private final UpdateGlobalOptionCommandHandler updateGlobalOptionCommandHandler;
    private final DeleteGlobalOptionCommandHandler deleteGlobalOptionCommandHandler;

    public GlobalOptionServiceImpl(ListAllGlobalOptionCommandHandler listAllGlobalOptionCommandHandler,
                                   GetGlobalOptionCommandHandler getGlobalOptionCommandHandler,
                                   CreateGlobalOptionCommandHandler createGlobalOptionCommandHandler,
                                   UpdateGlobalOptionCommandHandler updateGlobalOptionCommandHandler,
                                   DeleteGlobalOptionCommandHandler deleteGlobalOptionCommandHandler) {
        this.listAllGlobalOptionCommandHandler = listAllGlobalOptionCommandHandler;
        this.getGlobalOptionCommandHandler = getGlobalOptionCommandHandler;
        this.createGlobalOptionCommandHandler = createGlobalOptionCommandHandler;
        this.updateGlobalOptionCommandHandler = updateGlobalOptionCommandHandler;
        this.deleteGlobalOptionCommandHandler = deleteGlobalOptionCommandHandler;
    }

    @Override
    public ListAllGlobalOptionResponse listAllGlobalOption(ListAllGlobalOptionCommand command) {
        return listAllGlobalOptionCommandHandler.handler(command);
    }

    @Override
    public OptionResponse getGlobalOption(GetGlobalOptionCommand command) {
        return getGlobalOptionCommandHandler.handler(command);
    }

    @Override
    public OptionResponse createGlobalOption(CreateGlobalOptionCommand command) {
        return createGlobalOptionCommandHandler.handler(command);
    }

    @Override
    public OptionResponse updateGlobalOption(UpdateGlobalOptionCommand command) {
        return updateGlobalOptionCommandHandler.handler(command);
    }

    @Override
    public void deleteGlobalOption(DeleteGlobalOptionCommand command) {
        deleteGlobalOptionCommandHandler.handler(command);
    }
}
