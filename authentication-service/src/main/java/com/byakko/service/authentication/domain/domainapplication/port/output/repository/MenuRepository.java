package com.byakko.service.authentication.domain.domainapplication.port.output.repository;

import com.byakko.service.authentication.dataaccess.entity.MenuEntity;
import com.byakko.service.authentication.domain.domaincore.entity.Menu;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;
@Component
public interface MenuRepository {
    Optional<Menu> findById(int id);
    Menu save(Menu menu);
}
