package com.byakko.service.authentication.domain.domainapplication.port.output.repository;

import com.byakko.service.authentication.domain.domaincore.entity.Menu;

import java.util.Optional;

public interface MenuRepository {
    Optional<Menu> findById(int id);
}
