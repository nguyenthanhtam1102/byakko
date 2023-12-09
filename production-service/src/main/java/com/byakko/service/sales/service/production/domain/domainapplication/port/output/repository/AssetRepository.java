package com.byakko.service.sales.service.production.domain.domainapplication.port.output.repository;

import com.byakko.service.sales.service.production.domain.domaincore.entity.Asset;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface AssetRepository {

    Asset save(Asset asset);
    Page<Asset> findAll(int page, int limit, String query);
    Optional<Asset> findById(String id);
    void delete(Asset asset);

}
