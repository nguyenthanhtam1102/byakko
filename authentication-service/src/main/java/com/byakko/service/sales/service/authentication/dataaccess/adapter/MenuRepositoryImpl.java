package com.byakko.service.sales.service.authentication.dataaccess.adapter;

import com.byakko.service.sales.service.authentication.dataaccess.repository.MenuJpaRepository;
import com.byakko.service.sales.service.authentication.domain.domainapplication.mapper.MenuMapper;
import com.byakko.service.sales.service.authentication.domain.domainapplication.port.output.repository.MenuRepository;
import com.byakko.service.sales.service.authentication.domain.domaincore.entity.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@RequiredArgsConstructor
public class MenuRepositoryImpl implements MenuRepository {
    private final MenuJpaRepository menuJpaRepository;
    @Override
    public Optional<Menu> findById(int id) {
       return menuJpaRepository.findById(id).map(MenuMapper::toMenu);
    }

    @Override
    public Menu save(Menu menu) {
        return MenuMapper.toMenu(menuJpaRepository.save(MenuMapper.toMenuEntity(menu)));
    }

}
