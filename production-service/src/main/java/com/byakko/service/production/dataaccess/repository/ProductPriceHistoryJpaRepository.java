package com.byakko.service.production.dataaccess.repository;

import com.byakko.service.production.dataaccess.entity.ProductEntity;
import com.byakko.service.production.dataaccess.entity.ProductPriceHistoryEntity;
import com.byakko.service.production.dataaccess.entity.ProductPriceHistoryEntityId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductPriceHistoryJpaRepository extends JpaRepository<ProductPriceHistoryEntity, ProductPriceHistoryEntityId> {

    Page<ProductPriceHistoryEntity> findByProductOrderByStartDateDesc(ProductEntity product, Pageable pageable);
    Optional<ProductPriceHistoryEntity> findByProductAndEndDateIsNull(ProductEntity product);

}
