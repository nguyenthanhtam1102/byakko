package com.byakko.service.production.domain.domainapplication.handler.global_option;

import com.byakko.common.application.dto.ListAllResponse;
import com.byakko.service.production.domain.domainapplication.dto.global_option.ListAllGlobalOptionCommand;
import com.byakko.service.production.domain.domainapplication.dto.global_option.ListAllGlobalOptionResponse;
import com.byakko.service.production.domain.domainapplication.mapper.OptionMapper;
import com.byakko.service.production.domain.domainapplication.port.output.repository.GlobalOptionRepository;
import com.byakko.service.production.domain.domaincore.entity.GlobalOption;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ListAllGlobalOptionCommandHandler {

    private final GlobalOptionRepository globalOptionRepository;

    public ListAllGlobalOptionCommandHandler(GlobalOptionRepository globalOptionRepository) {
        this.globalOptionRepository = globalOptionRepository;
    }

    public ListAllGlobalOptionResponse handler(ListAllGlobalOptionCommand command) {
        Page<GlobalOption> page = globalOptionRepository.findAll(command.getPage(), command.getLimit(), command.getQuery());

        return ListAllGlobalOptionResponse.builder()
                .data(page.get().map(OptionMapper::toOptionResponse).toList())
                .pagination(ListAllResponse.Pagination.toPagination(page))
                .build();
    }

}
