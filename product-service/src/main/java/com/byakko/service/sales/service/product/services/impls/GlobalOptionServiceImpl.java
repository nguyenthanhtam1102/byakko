package com.byakko.service.sales.service.product.services.impls;

import com.byakko.service.sales.common.DomainConstants;
import com.byakko.service.sales.common.application.dto.ListAllResponse;
import com.byakko.service.sales.common.domain.exception.NotFoundException;
import com.byakko.service.sales.common.domain.exception.ValidationException;
import com.byakko.service.product.dtos.global_option.*;
import com.byakko.service.sales.service.product.mappers.OptionMapper;
import com.byakko.service.sales.service.product.models.GlobalOption;
import com.byakko.service.sales.service.product.models.GlobalOptionValue;
import com.byakko.service.sales.service.product.repositories.GlobalOptionRepository;
import com.byakko.service.sales.service.product.repositories.GlobalOptionValueRepository;
import com.byakko.service.sales.service.product.services.GlobalOptionService;
import com.byakko.service.sales.service.product.dtos.global_option.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GlobalOptionServiceImpl implements GlobalOptionService {

    private final GlobalOptionRepository globalOptionRepository;
    private final GlobalOptionValueRepository globalOptionValueRepository;

    @Override
    public ListAllOptionsResponse listAllOptions(ListAllOptionsCommand command) {
        Sort.Direction direction;
        if(command.getSortDirection() == null || command.getSortDirection().isBlank()) {
            direction = Sort.Direction.DESC;
        } else if(command.getSortDirection().equalsIgnoreCase("DESC")) {
            direction = Sort.Direction.DESC;
        } else if(command.getSortDirection().equalsIgnoreCase("ASC")) {
            direction = Sort.Direction.ASC;
        } else {
            throw new RuntimeException("Sort direction not correct");
        }

        Sort sort;
        if(command.getSortBy() == null || command.getSortBy().isBlank()) {
            sort = Sort.by(direction, "createdAt");
        } else if(command.getSortBy().equalsIgnoreCase("NAME")) {
            sort = Sort.by(direction, "name");
        } else {
            throw new RuntimeException("Sort by not correct");
        }

        Pageable pageable = PageRequest.of(command.getPage(), command.getLimit(), sort);

        Page<GlobalOption> page = globalOptionRepository.findAllByIdOrName(
                "%" + (command.getQuery() != null ? command.getQuery().toLowerCase() : "") + "%",
                pageable
        );

        return ListAllOptionsResponse.builder()
                .data(page.stream().map(OptionMapper::toOptionResponse).toList())
                .pagination(ListAllResponse.Pagination.toPagination(page))
                .build();
    }

    @Override
    public OptionResponse getOption(GetOptionCommand command) {
        GlobalOption option = globalOptionRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("option with id %s not found", command.getId())));
        return OptionMapper.toOptionResponse(option);
    }

    @Transactional
    @Override
    public OptionResponse createOption(CreateOptionCommand command) {
        GlobalOption option = new GlobalOption();
        option.setName(command.getName());

        if(command.getValues() != null && !command.getValues().isEmpty()) {
            option.setValues(
                    command.getValues().stream().map(valueName -> {
                        GlobalOptionValue value = new GlobalOptionValue();
                        value.setName(valueName);
                        value.setOption(option);
                        return value;
                    }).collect(Collectors.toSet())
            );
        }

        globalOptionRepository.save(option);

        return OptionMapper.toOptionResponse(option);
    }

    @Transactional
    @Override
    public OptionResponse updateOption(UpdateOptionCommand command) {
        GlobalOption option = globalOptionRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("option with id %s not found", command.getId())));

        if(command.getName() != null) {
            if(command.getName().isBlank())
                throw new ValidationException(Map.of("name", "name must be not blank"));
            option.setName(command.getName());
        }

        if(command.getValues() != null) {
            globalOptionValueRepository.deleteAllInBatch(option.getValues());

            option.setValues(
                    command.getValues().stream().map(valueName -> {
                        GlobalOptionValue value = new GlobalOptionValue();
                        value.setName(valueName);
                        value.setOption(option);
                        return value;
                    }).collect(Collectors.toSet())
            );
        }

        option.setUpdatedAt(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)));
        globalOptionRepository.save(option);

        return OptionMapper.toOptionResponse(option);
    }

    @Transactional
    @Override
    public void deleteOption(DeleteOptionCommand command) {
        GlobalOption option = globalOptionRepository.findById(command.getId())
                .orElseThrow(() -> new NotFoundException(String.format("option with id %s not found", command.getId())));
        globalOptionRepository.delete(option);
    }

}
