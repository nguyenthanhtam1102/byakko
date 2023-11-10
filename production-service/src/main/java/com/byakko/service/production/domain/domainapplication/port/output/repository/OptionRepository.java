package com.byakko.service.production.domain.domainapplication.port.output.repository;

import com.byakko.service.production.domain.domaincore.entity.GlobalOption;
import com.byakko.service.production.domain.domaincore.entity.Option;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface OptionRepository {

    Option save(Option option);
    Optional<Option> findById(String id);
    Page<Option> findAll(int page, int limit, String query);
    void delete(Option option);

}
