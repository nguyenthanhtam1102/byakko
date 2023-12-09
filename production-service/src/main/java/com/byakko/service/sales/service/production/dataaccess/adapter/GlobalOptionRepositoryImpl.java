package com.byakko.service.sales.service.production.dataaccess.adapter;

import com.byakko.service.sales.service.production.dataaccess.entity.GlobalOptionEntity;
import com.byakko.service.sales.service.production.dataaccess.mapper.GlobalOptionMapper;
import com.byakko.service.sales.service.production.dataaccess.repository.GlobalOptionJpaRepository;
import com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository.GlobalOptionRepository;
import com.byakko.service.sales.service.production.domain.domaincore.entity.GlobalOption;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GlobalOptionRepositoryImpl implements GlobalOptionRepository {

    private final GlobalOptionJpaRepository globalOptionJpaRepository;


    @Override
    public GlobalOption save(GlobalOption option) {
        GlobalOptionEntity optionEntity = globalOptionJpaRepository.save(GlobalOptionMapper.toGlobalOptionEntity(option));
        return GlobalOptionMapper.toGlobalOption(optionEntity);
    }

    @Override
    public Optional<GlobalOption> findById(String id) {
        return globalOptionJpaRepository.findById(id).map(GlobalOptionMapper::toGlobalOption);
    }

    @Override
    public Page<GlobalOption> findAll(int page, int limit, String query) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
        return globalOptionJpaRepository.findByOptionNameOrOptionValueName("%" + query + "%", "%" + query + "%", pageable)
                .map(GlobalOptionMapper::toGlobalOption);
    }

    @Override
    public void delete(GlobalOption option) {
        globalOptionJpaRepository.deleteById(option.getId().getValue());
    }
}
