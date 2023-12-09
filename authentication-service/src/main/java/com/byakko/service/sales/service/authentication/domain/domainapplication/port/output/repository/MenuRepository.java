package com.byakko.service.sales.service.authentication.domain.domainapplication.port.output.repository;

import com.byakko.service.sales.service.authentication.domain.domaincore.entity.Menu;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public interface MenuRepository {
    Optional<Menu> findById(int id);
    Menu save(Menu menu);
}
