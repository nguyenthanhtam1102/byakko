package com.byakko.service.sales.service.production.dataaccess.repository;

import com.byakko.service.sales.service.production.dataaccess.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, String> {

    @Query(value = "select p from ProductEntity p " +
            "where lower(p.id) like lower(:idOrName) or lower(p.name) like lower(:idOrName) ")
    Page<ProductEntity> findAllByIdOrName(String idOrName, Pageable pageable);

}
