package com.byakko.service.production.dataaccess.repository;

import com.byakko.service.production.dataaccess.entity.ProductEntity;
import com.byakko.service.production.dataaccess.entity.ProductPriceEntity;
import com.byakko.service.production.dataaccess.entity.ProductPriceEntityId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductPriceJpaRepository extends JpaRepository<ProductPriceEntity, ProductPriceEntityId> {

    Page<ProductPriceEntity> findByProductOrderByStartDateDesc(ProductEntity product, Pageable pageable);
    Optional<ProductPriceEntity> findByProductAndEndDateIsNull(ProductEntity product);

}
