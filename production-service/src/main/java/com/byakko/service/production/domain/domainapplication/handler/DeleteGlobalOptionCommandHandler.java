package com.byakko.service.production.domain.domainapplication.handler;

import com.byakko.service.production.domain.domainapplication.dto.DeleteGlobalOptionCommand;
import com.byakko.service.production.domain.domainapplication.port.output.repository.GlobalOptionRepository;
import com.byakko.service.production.domain.domaincore.entity.GlobalOption;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeleteGlobalOptionCommandHandler {

    private final GlobalOptionRepository globalOptionRepository;
    private final GlobalOptionCommandHandlerHelper globalOptionCommandHandlerHelper;

    public DeleteGlobalOptionCommandHandler(GlobalOptionRepository globalOptionRepository,
                                            GlobalOptionCommandHandlerHelper globalOptionCommandHandlerHelper) {
        this.globalOptionRepository = globalOptionRepository;
        this.globalOptionCommandHandlerHelper = globalOptionCommandHandlerHelper;
    }

    @Transactional
    public void handler(DeleteGlobalOptionCommand command) {
        GlobalOption option = globalOptionCommandHandlerHelper.findOptionById(command.getId());
        globalOptionRepository.delete(option);
    }

}
