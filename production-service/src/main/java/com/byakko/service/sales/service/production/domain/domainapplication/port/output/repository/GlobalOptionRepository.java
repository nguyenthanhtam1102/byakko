package com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository;

import com.byakko.service.sales.service.production.domain.domaincore.entity.GlobalOption;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface GlobalOptionRepository {

    GlobalOption save(GlobalOption option);
    Optional<GlobalOption> findById(String id);
    Page<GlobalOption> findAll(int page, int limit, String query);
    void delete(GlobalOption option);

}
